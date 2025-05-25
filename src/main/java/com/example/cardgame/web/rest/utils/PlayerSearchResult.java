package com.example.cardgame.web.rest.utils;

import com.example.cardgame.model.Player;

public class PlayerSearchResult {

    private final Status status;
    private final Player player;

    private enum Status {
        ROOM_NOT_FOUND,
        PLAYER_NOT_FOUND,
        SUCCESS
    }

    public PlayerSearchResult(Status status, Player player) {
        this.status = status;
        this.player = player;
    }

    public static PlayerSearchResult roomNotFound() {
        return new PlayerSearchResult(Status.ROOM_NOT_FOUND, null);
    }

    public static PlayerSearchResult playerNotFound() {
        return new PlayerSearchResult(Status.PLAYER_NOT_FOUND, null);
    }

    public static PlayerSearchResult success(Player player) {
        return new PlayerSearchResult(Status.SUCCESS, player);
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }

    public Player getPlayer() {
        if (!isSuccess()) {
            throw new IllegalStateException("Player not available");
        }
        return player;
    }
}
