package com.example.cardgame.web.socket.messages.bodies;

public class PlayerLeftMessageBody extends MessageBody{

    private final int playersCount;
    private final String playerLogin;

    public PlayerLeftMessageBody(int playersCount, String playerLogin) {
        this.playersCount = playersCount;
        this.playerLogin = playerLogin;
    }

    public int getPlayersCount() {
        return playersCount;
    }

    public String getPlayerLogin() {
        return playerLogin;
    }

}
