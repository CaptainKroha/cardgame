package com.example.cardgame.web.socket.messages.bodies;

import com.example.cardgame.model.card.SituationCard;
import lombok.Getter;

@Getter
public class SituationCardChangedMessageBody extends MessageBody {

    private final SituationCard newCard;

    public SituationCardChangedMessageBody(SituationCard newCard) {
        this.newCard = newCard;
    }

}
