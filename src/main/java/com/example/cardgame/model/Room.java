package com.example.cardgame.model;

import com.example.cardgame.model.card.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Setter
@Getter
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
    private List<ActionCard> droppedActionCards = new ArrayList<>();
    private Date createdAt = new Date();
    private Boolean isGameStarted = false;

    public Optional<Player> findPlayerById(String playerId) {
        return players.stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst();
    }
}
