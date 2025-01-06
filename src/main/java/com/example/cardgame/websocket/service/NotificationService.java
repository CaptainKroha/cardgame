package com.example.cardgame.websocket.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyPlayers(String roomId, Object message) {
        messagingTemplate.convertAndSend("/topic/"+roomId, message);
    }

    public void notifySender(String sessionId, Object message) {
        if (message instanceof Exception exception) {
            sendErrorToSender(sessionId, exception.getMessage());
        } else {
            sendMessageToSender(sessionId, message);
        }
    }

    private void sendErrorToSender(String sessionId, String errorMessage) {
        messagingTemplate.convertAndSendToUser(
                sessionId,
                "/queue/errors",
                errorMessage
        );
    }

    private void sendMessageToSender(String sessionId, Object message) {
        messagingTemplate.convertAndSendToUser(
                sessionId,
                "/queue/response",
                message
        );
    }
}
