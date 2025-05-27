package com.example.cardgame.web.rest.service;

import com.example.cardgame.model.Player;
import com.example.cardgame.utils.PlayerCardChanger;
import com.example.cardgame.utils.RoomCardChanger;
import com.example.cardgame.web.exceptions.CardLimitReachedException;
import com.example.cardgame.web.socket.messages.WebSocketMessage;
import com.example.cardgame.web.socket.messages.bodies.ActionCardDroppedMessageBody;
import com.example.cardgame.web.socket.messages.bodies.SituationCardChangedMessageBody;
import com.example.cardgame.web.socket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private NotificationService notificationService;

    public Optional<Player> changeRoleCard(String roomId, String playerId) {
        return roomService.getRoomById(roomId)
                .flatMap(room -> room.findPlayerById(playerId)
                        .map(player -> {
                            PlayerCardChanger changer = new PlayerCardChanger(room);
                            synchronized (room) {
                                changer.changeCurrentRoleCardFor(player);
                                roomService.saveRoom(room);
                                return player;
                            }
                        })
                );
    }

    public Optional<Player> changeMoodCard(String roomId, String playerId) {
        return roomService.getRoomById(roomId)
                .flatMap(room -> room.findPlayerById(playerId)
                        .map(player -> {
                            PlayerCardChanger changer = new PlayerCardChanger(room);
                            synchronized (room) {
                                changer.changeCurrentMoodCardFor(player);
                                roomService.saveRoom(room);
                                return player;
                            }
                        })
                );
    }

    public boolean changeSituationCard(String roomId) {
        return roomService.getRoomById(roomId)
                .map(room -> {
                    synchronized (room) {
                        RoomCardChanger changer = new RoomCardChanger(room);
                        changer.changeCurrentSituationCard();
                        roomService.saveRoom(room);

                        SituationCardChangedMessageBody messageBody =
                                new SituationCardChangedMessageBody(room.getSituationCard());
                        notificationService.notifyPlayers(
                                roomId,
                                WebSocketMessage.situationCardChanged(messageBody)
                        );
                        return true;
                    }
                })
                .orElse(false);
    }

    public boolean dropActionCard(String roomId, String playerId, String cardId) {
        return roomService.getRoomById(roomId)
                .flatMap(room -> room.findPlayerById(playerId)
                        .flatMap(player -> player.fingActionCardById(cardId)
                                .map(card -> {
                                    synchronized (room){
                                        player.getActionCards().remove(card);
                                        room.getDroppedActionCards().add(card);
                                        roomService.saveRoom(room);

                                        ActionCardDroppedMessageBody messageBody =
                                                new ActionCardDroppedMessageBody(card);
                                        notificationService.notifyPlayers(
                                                roomId,
                                                WebSocketMessage.actionCardDropped(messageBody)
                                        );
                                        return true;
                                    }
                                })
                        )
                )
                .orElse(false);
    }

    public Optional<Player> getActionCard(String roomId, String playerId) throws CardLimitReachedException {
        return roomService.getRoomById(roomId)
                .flatMap(room -> room.findPlayerById(playerId)
                        .map(player -> {
                            synchronized (room) {
                                if(player.getActionCards().size() >= room.getCardsPerPlayer()) {
                                    throw new CardLimitReachedException(
                                            "Player " + playerId + " already has maximum cards (" +
                                                    room.getCardsPerPlayer() + ")"
                                    );
                                }
                                PlayerCardChanger changer = new PlayerCardChanger(room);
                                changer.getActionCardFor(player);
                                roomService.saveRoom(room);

                                if(room.getActionCards().isEmpty()) {
                                    notificationService.notifyPlayers(
                                            roomId,
                                            WebSocketMessage.deckIsOver()
                                    );
                                }
                                return player;
                            }
                        })
                );
    }
}
