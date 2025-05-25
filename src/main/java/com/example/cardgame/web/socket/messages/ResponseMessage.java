package com.example.cardgame.web.socket.messages;

public enum ResponseMessage {
    GAME_STARTED,
    GAME_STOPPED,
    SITUATION_CARD_CHANGED,
    ACTION_CARD_DROPPED,
    DECK_IS_OVER,
    PLAYER_LEFT,
    PLAYER_ENTER
}
