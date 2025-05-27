package com.example.cardgame.model;

import com.example.cardgame.generator.UUIDGenerator;
import com.example.cardgame.model.card.ActionCard;
import com.example.cardgame.model.card.MoodCard;
import com.example.cardgame.model.card.RoleCard;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Setter
@Getter
public class Player {
    // getters and setters
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


    public Optional<ActionCard> fingActionCardById(String cardId) {
        return actionCards.stream()
                .filter(c -> c.getId().equals(cardId))
                .findFirst();
    }
}
