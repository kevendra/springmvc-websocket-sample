package com.example.web.ws.config2;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 
 * @author Kevendra Patidar
 */
public class TextHandler extends TextWebSocketHandler {

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        //System.out.println("Path " + session.getUri().getPath() + " Host " + session.getUri().getHost() + " Port " + session.getUri().getPort());
        TextMessage msg = new TextMessage("Hello, " + message.getPayload() + "!");
        session.sendMessage(msg);
    }
}