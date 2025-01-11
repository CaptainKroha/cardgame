package com.example.cardgame.web.socket.exception;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException() {
        super("Room not found");
    }
}
