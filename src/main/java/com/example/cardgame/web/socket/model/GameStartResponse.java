package com.example.cardgame.web.socket.model;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.model.card.SituationCard;
import com.example.cardgame.web.socket.ResponseMessages;

import java.util.List;

public class GameStartResponse {

    private final String message = ResponseMessages.GAME_STARTED;
    private SituationCard situationCard;
    private List<Player> players;

    private GameStartResponse() {

    }

    public static GameStartResponse get(Room room) {
        GameStartResponse response = new GameStartResponse();
        response.situationCard = room.getSituationCard();
        response.players = room.getPlayers();
        return response;
    }

    public String getMessage() {
        return message;
    }

    public SituationCard getSituationCard() {
        return situationCard;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
