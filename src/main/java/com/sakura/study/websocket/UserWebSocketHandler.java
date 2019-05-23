package com.sakura.study.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sakura.study.dao.CommunicationRecordMapper;
import com.sakura.study.dto.EmployeeSession;
import com.sakura.study.dto.Message;
import com.sakura.study.model.CommunicationRecord;
import com.sakura.study.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

@ServerEndpoint("/conversation/{userId}")
@Component
@Lazy
public class UserWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserWebSocketHandler.class);//日志

    private SessionContext sessionContext = (SessionContext) SpringContextUtil.getBeanByClass(SessionContext.class);

    private CommunicationRecordMapper dao = (CommunicationRecordMapper) SpringContextUtil.getBeanByClass(CommunicationRecordMapper.class);

    @OnMessage
    /**
         * 用户发消息给客服
     * @param message
     * @param userSession
     * @param userId
     * @throws IOException
     * @throws EncodeException
     */
    public void onMessage(String message,Session userSession,@PathParam("userId") Integer userId) throws IOException, EncodeException {
        logger.info(message);//打日志
        EmployeeSession session = sessionContext.getUserForEmployee().get(userSession);
        ObjectMapper mapper = new ObjectMapper();
        if(session != null){
            Message data = new Message();
            data.setContent(message);
            data.setTime(new Date());
            data.setType(1);
            data.setUserId(userId);
            String msg = mapper.writeValueAsString(data);
            session.getSession().getAsyncRemote().sendText(msg);
            //在数据库中记录发送的消息
            CommunicationRecord cr = buildModel(message,userId,session.getEmployeeId());
            dao.insertSelective(cr);
        }
    }

    /**
     * 建立连接
     * @param session
     * @param userId
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
        if(sessionContext.getEmployeeQueue().size() != 0){
            EmployeeSession employeeSession = sessionContext.getEmployeeQueue().poll();//poll出队
            sessionContext.getUserForEmployee().put(session,employeeSession);//userForEmployee 建立连接
            sessionContext.getEmployeeForUsers().put(employeeSession.getEmployeeId(),session);
            sessionContext.getEmployeeQueue().add(employeeSession);//放到队尾
        } else {
            sessionContext.getUserWaitQueue().add(session);
        }
        sessionContext.getUserSession().put(userId,session);
    }

    @OnClose
    public void onClose(Session session,@PathParam("userId") Integer userId){
        logger.info("连接已经被关闭");
        sessionContext.getUserWaitQueue().remove(session);
        EmployeeSession es = sessionContext.getUserForEmployee().get(session);
        if(es != null) {
            sessionContext.getEmployeeForUsers().remove(es.getEmployeeId(), session);
        }
        sessionContext.getUserForEmployee().remove(session);
        sessionContext.getUserSession().remove(userId);
    }

    @OnError
    public void onError(Throwable t){
        logger.error(t.getMessage());
    }

    private CommunicationRecord buildModel(String message,Integer userId,Integer employeeId){//用户给客服发消息会发一个userid
        CommunicationRecord cr = new CommunicationRecord();
        cr.setContent(message);
        cr.setEmployeeId(employeeId);
        cr.setUserId(userId);
        cr.setSenderType(0);
        return cr;
    }
}
