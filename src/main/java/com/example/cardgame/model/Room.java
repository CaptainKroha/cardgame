package com.example.cardgame.model;

import com.example.cardgame.model.card.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "rooms")
public class Room {
    @Id
    private String roomId;
    private String creatorId;
    private Integer cardsPerPlayer;
    private SituationCard situationCard;
    private List<Player> players = new ArrayList<>();
    private List<SituationCard> situationCards;
    private List<RoleCard> roleCards;
    private List<MoodCard> moodCards;
    private List<ActionCard> actionCards;
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

    public SituationCard getSituationCard() {
        return situationCard;
    }

    public void setSituationCard(SituationCard situationCard) {
        this.situationCard = situationCard;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<SituationCard> getSituationCards() {
        return situationCards;
    }

    public void setSituationCards(List<SituationCard> situationCards) {
        this.situationCards = situationCards;
    }

    public List<RoleCard> getRoleCards() {
        return roleCards;
    }

    public void setRoleCards(List<RoleCard> roleCards) {
        this.roleCards = roleCards;
    }

    public List<MoodCard> getMoodCards() {
        return moodCards;
    }

    public void setMoodCards(List<MoodCard> moodCards) {
        this.moodCards = moodCards;
    }

    public List<ActionCard> getActionCards() {
        return actionCards;
    }

    public void setActionCards(List<ActionCard> actionCards) {
        this.actionCards = actionCards;
    }

    public Boolean getGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(Boolean gameStarted) {
        isGameStarted = gameStarted;
    }
}
