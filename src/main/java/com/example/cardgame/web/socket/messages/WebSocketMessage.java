package com.example.cardgame.web.socket.messages;


import com.example.cardgame.web.socket.messages.bodies.GameStartMessageBody;
import com.example.cardgame.web.socket.messages.bodies.MessageBody;
import com.example.cardgame.web.socket.messages.bodies.PlayerEnterMessageBody;

public class WebSocketMessage {
    private final ResponseMessage message;
    private final MessageBody body;

    private WebSocketMessage(ResponseMessage message) {
        this.message = message;
        this.body = null;
    }

    private WebSocketMessage(
            ResponseMessage message,
            MessageBody body) {
        this.message = message;
        this.body = body;
    }

    public ResponseMessage getMessage() {
        return message;
    }

    public MessageBody getBody() {
        return body;
    }

    public static WebSocketMessage gameStarted(GameStartMessageBody body) {
        return new WebSocketMessage(ResponseMessage.GAME_STARTED, body);
    }

    public static WebSocketMessage gameStopped() {
        return new WebSocketMessage(ResponseMessage.GAME_STOPPED);
    }

    public static WebSocketMessage playerEntered(PlayerEnterMessageBody body) {
        return new WebSocketMessage(ResponseMessage.PLAYER_ENTER, body);
    }
}
