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

    public void changeCurrentRoleCardFor(Player player) {
        RoleCard newCard = swapCurrentCard(room.getRoleCards(), player.getRoleCard());
        player.setRoleCard(newCard);
    }

    public void changeCurrentMoodCardFor(Player player) {
        MoodCard newCard = swapCurrentCard(room.getMoodCards(), player.getMoodCard());
        player.setMoodCard(newCard);
    }
}
