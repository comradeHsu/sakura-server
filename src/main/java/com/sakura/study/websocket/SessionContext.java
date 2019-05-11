package com.sakura.study.websocket;

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

    private Queue<Session> userWaitQueue = new ConcurrentLinkedQueue<>();

    private Queue<EmployeeSession> employeeQueue = new ConcurrentLinkedQueue<>();

    private Map<Session,EmployeeSession> userForEmployee =  new ConcurrentHashMap<>();

    private Map<Integer,Session> userSession =  new ConcurrentHashMap<>();
}
