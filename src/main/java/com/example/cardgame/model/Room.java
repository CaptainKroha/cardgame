package com.example.cardgame.model;

import com.example.cardgame.generator.UUIDGenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "rooms")
public class Room {
    @Id
    private String roomId = UUIDGenerator.getNew();
    private String simpleRoomId;
    private String creatorId;
    private Integer cardsPerPlayer;
    private Card situationCard;
    private List<Player> players;
    private List<Card> situationCards;
    private List<Card> roleCards;
    private List<Card> moodCards;
    private List<Card> actionCards;
    private Date createdAt = new Date();
    private Boolean isGameStarted = false;

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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

    public Integer getCardsPerPlayer() {
        return cardsPerPlayer;
    }

    public void setCardsPerPlayer(Integer cardsPerPlayer) {
        this.cardsPerPlayer = cardsPerPlayer;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public Card getSituationCard() {
        return situationCard;
    }

    public void setSituationCard(Card situationCard) {
        this.situationCard = situationCard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getSituationCards() {
        return situationCards;
    }

    public void setSituationCards(List<Card> situationCards) {
        this.situationCards = situationCards;
    }

    public List<Card> getRoleCards() {
        return roleCards;
    }

    public void setRoleCards(List<Card> roleCards) {
        this.roleCards = roleCards;
    }

    public List<Card> getMoodCards() {
        return moodCards;
    }

    public void setMoodCards(List<Card> moodCards) {
        this.moodCards = moodCards;
    }

    public List<Card> getActionCards() {
        return actionCards;
    }

    public void setActionCards(List<Card> actionCards) {
        this.actionCards = actionCards;
    }

    public Boolean getGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(Boolean gameStarted) {
        isGameStarted = gameStarted;
    }
}
