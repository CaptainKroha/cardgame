package com.example.cardgame.controller;

public class CreateRoomResponse {
    private String roomId;
    private String simpleRoomId;

    public CreateRoomResponse(String roomId, String simpleRoomId) {
        this.roomId = roomId;
        this.simpleRoomId = simpleRoomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getSimpleRoomId() {
        return simpleRoomId;
    }

    public void setSimpleRoomId(String simpleRoomId) {
        this.simpleRoomId = simpleRoomId;
    }
}
