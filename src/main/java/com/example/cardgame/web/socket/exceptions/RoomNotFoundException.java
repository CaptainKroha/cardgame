package com.example.cardgame.web.socket.exceptions;

public class RoomNotFoundException extends Exception {
    public RoomNotFoundException() {
        super("Room not found");
    }
}
