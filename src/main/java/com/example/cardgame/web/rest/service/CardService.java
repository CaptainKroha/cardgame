package com.example.cardgame.web.rest.service;

import com.example.cardgame.model.card.MoodCard;
import com.example.cardgame.utils.PlayerCardChanger;
import com.example.cardgame.model.Player;
import com.example.cardgame.model.card.RoleCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    private RoomService roomService;

    public Optional<Player> changeRoleCard(String roomId, String playerId) {
        return roomService.getRoomById(roomId)
                .flatMap(room -> room.findPlayerById(playerId)
                        .map(player -> {
                            PlayerCardChanger changer = new PlayerCardChanger(room);
                            RoleCard newCard = changer.findNewRoleCardFor(player);
                            synchronized (room) {
                                player.setRoleCard(newCard);
                                roomService.saveRoom(room);
                            }
                            return player;
                        })
                );
    }

    public Optional<Player> changeMoodCard(String roomId, String playerId) {
        return roomService.getRoomById(roomId)
                .flatMap(room -> room.findPlayerById(playerId)
                        .map(player -> {
                            PlayerCardChanger changer = new PlayerCardChanger(room);
                            MoodCard newCard = changer.findNewMoodCardFor(player);
                            synchronized (room) {
                                player.setMoodCard(newCard);
                                roomService.saveRoom(room);
                            }
                            return player;
                        })
                );
    }
}
