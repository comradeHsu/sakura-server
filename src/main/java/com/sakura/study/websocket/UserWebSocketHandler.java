package com.sakura.study.websocket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/conversation/{userId}")
public class UserWebSocketHandler {

    @OnMessage
    public void onMessage(String message) {
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Integer userId) {
    }

    @OnClose
    public void onClose(){
    }

    @OnError
    public void onError(){

    }
}
