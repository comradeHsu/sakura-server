//package com.sakura.study.config;
//
//import com.sakura.study.dto.EmployeeSession;
//import com.sakura.study.websocket.SessionContext;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import javax.websocket.Session;
//import java.time.LocalDateTime;
//
//@Configuration
//@EnableScheduling
//public class ScheduleTask {
//
//    @Autowired
//    SessionContext sessionContext;
//
//    @Scheduled(fixedRate=5000)
//    private void configureTasks() {
//        EmployeeSession employeeSession = sessionContext.getEmployeeQueue().poll();
//        Session userSession = sessionContext.getUserWaitQueue().poll();
//        if(employeeSession != null && userSession != null){
//            sessionContext.getUserForEmployee().put(userSession,employeeSession);
//        }
//        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//    }
//}
