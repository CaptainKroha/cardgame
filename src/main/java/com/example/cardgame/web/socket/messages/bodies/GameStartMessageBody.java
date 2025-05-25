package com.example.cardgame.web.socket.messages.bodies;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.model.card.SituationCard;

import java.util.List;

public class GameStartMessageBody extends MessageBody {

    private SituationCard situationCard;
    private List<Player> players;

    private GameStartMessageBody() {

    }

    public static GameStartMessageBody get(Room room) {
        GameStartMessageBody response = new GameStartMessageBody();
        response.situationCard = room.getSituationCard();
        response.players = room.getPlayers();
        return response;
    }

    public SituationCard getSituationCard() {
        return situationCard;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
