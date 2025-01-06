package com.example.cardgame.websocket.controller;

import com.example.cardgame.websocket.exception.GameStartException;
import com.example.cardgame.websocket.model.GameStartRequest;
import com.example.cardgame.websocket.model.GameStartResponse;
import com.example.cardgame.websocket.service.GameService;
import com.example.cardgame.websocket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private NotificationService notificationService;

    @MessageMapping("/{roomId}/start")
    public void startGame(GameStartRequest request, @Header("simpSessionId") String sessionId) {
        try {
            // Попытка старта игры через сервис
            GameStartResponse response = gameService.startGame(request);

            // Уведомляем всех подписчиков комнаты о старте игры
            notificationService.notifyPlayers(request.getRoomId(), response);

        } catch (GameStartException ex) {
            // Отправляем сообщение об ошибке только отправителю
            notificationService.notifySender(sessionId, ex);
        }
    }
}
