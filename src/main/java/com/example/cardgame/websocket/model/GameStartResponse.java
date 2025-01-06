package com.example.cardgame.websocket.model;

import com.example.cardgame.model.Room;

public class GameStartResponse {
    private String message;
    private Room room;

    public GameStartResponse(String message) {
        this.message = message;
    }

    public GameStartResponse(String message, Room room) {
        this(message);
        this.room = room;
    }
    public static GameStartResponse success(Room room) {
        return new GameStartResponse("Game started", room);
    }

}
