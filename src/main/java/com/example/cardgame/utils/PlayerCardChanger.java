package com.example.cardgame.utils;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.model.card.MoodCard;
import com.example.cardgame.model.card.RoleCard;

public class PlayerCardChanger implements CardChanger {
    private final Room room;

    public PlayerCardChanger(Room room) {
        this.room = room;
    }

    public RoleCard findNewRoleCardFor(Player player) {
        return getDifferentCard(room.getRoleCards(), player.getRoleCard());
    }

    public MoodCard findNewMoodCardFor(Player player) {
        return getDifferentCard(room.getMoodCards(), player.getMoodCard());
    }
}
