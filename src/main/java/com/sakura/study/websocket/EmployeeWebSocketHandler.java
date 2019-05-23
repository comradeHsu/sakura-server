package com.sakura.study.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Multimap;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/reply/{userId}")
@Component
@Lazy
public class EmployeeWebSocketHandler {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeWebSocketHandler.class);

    private SessionContext sessionContext = (SessionContext) SpringContextUtil.getBeanByClass(SessionContext.class);

    private CommunicationRecordMapper dao = (CommunicationRecordMapper) SpringContextUtil
            .getBeanByClass(CommunicationRecordMapper.class);

    @OnMessage
    public void onMessage(String message,@PathParam("userId") Integer employeeId) {
        ObjectMapper mapper = new ObjectMapper();
        Message data;
        try {
            data = mapper.readValue(message,Message.class);
            Session userSession = sessionContext.getUserSession().get(data.getUserId());
            if(userSession != null){
                data.setType(1);
                userSession.getAsyncRemote().sendText(message);
                CommunicationRecord cr = buildModel(data.getContent(),data.getUserId(),employeeId);
                dao.insertSelective(cr);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("解析数据出现异常");
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer employeeId) {
        EmployeeSession es = new EmployeeSession(employeeId,session);
        if(sessionContext.getEmployeeQueue().isEmpty() && !sessionContext.getUserWaitQueue().isEmpty()){
            Session userSession = sessionContext.getUserWaitQueue().poll();
            while(userSession != null){
                sessionContext.getUserForEmployee().put(userSession,es);
                sessionContext.getEmployeeForUsers().put(employeeId,userSession);
                userSession = sessionContext.getUserWaitQueue().poll();
            }
        }
        sessionContext.getEmployeeQueue().add(es);
        logger.info("当前共有{}个客服", sessionContext.getEmployeeQueue().size());
    }

    @OnClose
    public void onClose(Session session,@PathParam("userId") Integer userId) {
        EmployeeSession es = new EmployeeSession(userId,session);
        sessionContext.getEmployeeQueue().remove(es);
        Multimap<Integer,Session> users = sessionContext.getEmployeeForUsers();
        List<Session> userSessions = new ArrayList<>(users.get(userId));
        for (Session userSession : userSessions) {
            sessionContext.getUserWaitQueue().add(userSession);
        }
        users.removeAll(userId);
        Map<Session,EmployeeSession> userForEmployee = sessionContext.getUserForEmployee();
        Collection<EmployeeSession> values = userForEmployee.values();
        values.remove(es);
        logger.info("id={}连接已经被关闭", userId);
        logger.info("当前共有{}个客服", sessionContext.getEmployeeQueue().size());
    }

    @OnError
    public void onError(Throwable t) {
        logger.error(t.getMessage());
    }

    private CommunicationRecord buildModel(String message, Integer userId, Integer employeeId){
        CommunicationRecord cr = new CommunicationRecord();
        cr.setContent(message);
        cr.setEmployeeId(employeeId);
        cr.setUserId(userId);
        cr.setSenderType(1);
        return cr;
    }
}
