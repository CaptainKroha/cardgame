package com.example.cardgame.model;

import com.example.cardgame.generator.UUIDGenerator;

import java.util.List;

public class Player {
    private String playerId = UUIDGenerator.getNew();
    private String login;
    private Card roleCard;
    private Card moodCard;
    private List<Card> actionCards;

    public Player() {

    }

    public Player(String login) {
        if(login.isBlank()){
            this.login = String.format("Player№ %s", playerId);
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

    public Card getRoleCard() {
        return roleCard;
    }

    public void setRoleCard(Card roleCard) {
        this.roleCard = roleCard;
    }

    public Card getMoodCard() {
        return moodCard;
    }

    public void setMoodCard(Card moodCard) {
        this.moodCard = moodCard;
    }

    public List<Card> getActionCards() {
        return actionCards;
    }

    public void setActionCards(List<Card> actionCards) {
        this.actionCards = actionCards;
    }
}
