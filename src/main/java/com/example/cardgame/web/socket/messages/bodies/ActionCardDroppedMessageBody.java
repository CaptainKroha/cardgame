package com.example.cardgame.web.socket.messages.bodies;

import com.example.cardgame.model.card.ActionCard;
import lombok.Getter;

@Getter
public class ActionCardDroppedMessageBody extends MessageBody {

    private final ActionCard droppedCard;

    public ActionCardDroppedMessageBody(ActionCard droppedCard) {
        this.droppedCard = droppedCard;
    }

}
