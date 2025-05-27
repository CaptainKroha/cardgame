package com.example.cardgame.utils;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.model.card.ActionCard;
import com.example.cardgame.model.card.MoodCard;
import com.example.cardgame.model.card.RoleCard;

import java.util.List;

public class PlayerCardChanger implements CardChanger {
    private final Room room;

    public PlayerCardChanger(Room room) {
        this.room = room;
    }

    public void setRoleCardFor(Player player) {
        RoleCard newCard = getRandomCardFromList(room.getRoleCards());
        player.setRoleCard(newCard);
    }

    public void setMoodCardFor(Player player) {
        MoodCard newCard = getRandomCardFromList(room.getMoodCards());
        player.setMoodCard(newCard);
    }

    public void setActionCardsFor(Player player) {
        List<ActionCard> actionCards = getRandomCardsSetFromList(room.getActionCards(), room.getCardsPerPlayer());
        player.setActionCards(actionCards);
    }

    public void getActionCardFor(Player player) {
        ActionCard newCard = getRandomCardFromList(room.getActionCards());
        player.getActionCards().add(newCard);
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
