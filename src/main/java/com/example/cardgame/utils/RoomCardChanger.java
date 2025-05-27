package com.example.cardgame.utils;

import com.example.cardgame.model.Room;
import com.example.cardgame.model.card.SituationCard;

public class RoomCardChanger implements CardChanger {

    private final Room room;
    public RoomCardChanger(Room room) {
        this.room = room;
    }

    public void setSituationCard() {
        SituationCard newCard = getRandomCardFromList(room.getSituationCards());
        room.setSituationCard(newCard);
    }

    public void changeCurrentSituationCard() {
        SituationCard newCard = swapCurrentCard(room.getSituationCards(), room.getSituationCard());
        room.setSituationCard(newCard);
    }

}
