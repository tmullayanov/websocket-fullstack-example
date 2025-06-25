package com.wfh.example.backend.endpoints.websocket;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class SocketHandler extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage)
            throws InterruptedException, IOException {
        Map message = new Gson().fromJson(textMessage.getPayload(), Map.class);
        System.out.println(message.toString());
        try {
            session.sendMessage(new TextMessage("Hello " + message.getOrDefault("name", "user") + "!"));
        } catch (Exception e) {
            session.close();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // store session ids and bind it to user ids later
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
        System.out.printf("Closed %s. Status: %s\n", session.getId(), status.toString());
    }
}
