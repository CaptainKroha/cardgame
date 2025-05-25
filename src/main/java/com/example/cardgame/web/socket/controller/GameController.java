package com.example.cardgame.web.socket.controller;

import com.example.cardgame.web.socket.exception.RoomNotFoundException;
import com.example.cardgame.web.socket.messages.WebSocketMessage;
import com.example.cardgame.web.socket.model.GameStartRequest;
import com.example.cardgame.web.socket.service.GameService;
import com.example.cardgame.web.socket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class GameController {

    @Autowired
    private GameService gameService;
    @Autowired
    private NotificationService notificationService;


    @MessageMapping("/{roomId}/start")
    @SendTo("/topic/{roomId}")
    public WebSocketMessage startGame(
            @DestinationVariable String roomId,
            GameStartRequest request) throws RoomNotFoundException {
        return gameService.startGame(request);
    }

    @MessageMapping("/{roomId}/stop")
    @SendTo("/topic/{roomId}")
    public WebSocketMessage stopGame(@DestinationVariable String roomId) throws RoomNotFoundException {
        return gameService.stopGame(roomId);
    }
}
