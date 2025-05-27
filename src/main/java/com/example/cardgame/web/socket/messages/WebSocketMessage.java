package com.example.cardgame.web.socket.messages;


import com.example.cardgame.web.socket.messages.bodies.*;
import lombok.Getter;

@Getter
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

    public static WebSocketMessage gameStarted(GameStartMessageBody body) {
        return new WebSocketMessage(ResponseMessage.GAME_STARTED, body);
    }

    public static WebSocketMessage gameStopped() {
        return new WebSocketMessage(ResponseMessage.GAME_STOPPED);
    }

    public static WebSocketMessage playerEntered(PlayerEnterMessageBody body) {
        return new WebSocketMessage(ResponseMessage.PLAYER_ENTER, body);
    }

    public static WebSocketMessage playerLeft(PlayerLeftMessageBody body) {
        return new WebSocketMessage(ResponseMessage.PLAYER_LEFT, body);
    }

    public static WebSocketMessage situationCardChanged(SituationCardChangedMessageBody body) {
        return new WebSocketMessage(ResponseMessage.SITUATION_CARD_CHANGED, body);
    }

    public static WebSocketMessage actionCardDropped(ActionCardDroppedMessageBody body) {
        return new WebSocketMessage(ResponseMessage.ACTION_CARD_DROPPED, body);
    }

    public static WebSocketMessage deckIsOver() {
        return new WebSocketMessage(ResponseMessage.DECK_IS_OVER);
    }
}
