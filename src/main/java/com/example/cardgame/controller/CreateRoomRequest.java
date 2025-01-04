package com.example.cardgame.controller;

import java.util.List;

public class CreateRoomRequest {
    private String creatorLogin;
    private Integer cardsPerPlayer;
    private List<String> situationCards;
    private List<String> roleCards;
    private List<String> moodCards;
    private List<String> actionCards;

    // Getters и setters
    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }

    public Integer getCardsPerPlayer() {
        return cardsPerPlayer;
    }

    public void setCardsPerPlayer(Integer cardsPerPlayer) {
        this.cardsPerPlayer = cardsPerPlayer;
    }

    public List<String> getSituationCards() {
        return situationCards;
    }

    public void setSituationCards(List<String> situationCards) {
        this.situationCards = situationCards;
    }

    public List<String> getRoleCards() {
        return roleCards;
    }

    public void setRoleCards(List<String> roleCards) {
        this.roleCards = roleCards;
    }

    public List<String> getMoodCards() {
        return moodCards;
    }

    public void setMoodCards(List<String> moodCards) {
        this.moodCards = moodCards;
    }

    public List<String> getActionCards() {
        return actionCards;
    }

    public void setActionCards(List<String> actionCards) {
        this.actionCards = actionCards;
    }
}
