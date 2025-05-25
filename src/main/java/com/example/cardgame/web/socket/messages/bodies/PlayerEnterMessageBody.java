package com.example.cardgame.web.socket.messages.bodies;

public class PlayerEnterMessageBody extends MessageBody {

    private final int playersCount;
    private final String playerLogin;

    public PlayerEnterMessageBody(int playersCount, String playerLogin) {
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
