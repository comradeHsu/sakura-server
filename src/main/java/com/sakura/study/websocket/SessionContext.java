package com.sakura.study.websocket;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.sakura.study.dto.EmployeeSession;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.websocket.Session;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
@Getter
public class SessionContext {

    private Queue<Session> userWaitQueue = new ConcurrentLinkedQueue<>();//用户等待队列

    private Queue<EmployeeSession> employeeQueue = new ConcurrentLinkedQueue<>();//客服队列

    private Map<Session,EmployeeSession> userForEmployee =  new ConcurrentHashMap<>();//建立连接，第一个客服到队尾

    private Map<Integer,Session> userSession =  new ConcurrentHashMap<>();

    private Multimap<Integer,Session> employeeForUsers = ArrayListMultimap.create();
}
