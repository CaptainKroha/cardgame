package com.example.cardgame.model;

import com.example.cardgame.generator.UUIDGenerator;
import com.example.cardgame.model.card.ActionCard;
import com.example.cardgame.model.card.Card;
import com.example.cardgame.model.card.MoodCard;
import com.example.cardgame.model.card.RoleCard;

import java.util.List;

public class Player {
    private String playerId = UUIDGenerator.getNew();
    private String login;
    private RoleCard roleCard;
    private MoodCard moodCard;
    private List<ActionCard> actionCards;

    public Player() {

    }

    public Player(String login) {
        if(login.isBlank()){
            this.login = String.format("Player№ %s", playerId.substring(0, 4));
        } else {
            this.login = login;
        }
    }


    // getters and setters
    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleCard getRoleCard() {
        return roleCard;
    }

    public void setRoleCard(RoleCard roleCard) {
        this.roleCard = roleCard;
    }

    public MoodCard getMoodCard() {
        return moodCard;
    }

    public void setMoodCard(MoodCard moodCard) {
        this.moodCard = moodCard;
    }

    public List<ActionCard> getActionCards() {
        return actionCards;
    }

    public void setActionCards(List<ActionCard> actionCards) {
        this.actionCards = actionCards;
    }
}
