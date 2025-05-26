package com.example.cardgame.web.rest.service;

import com.example.cardgame.model.Player;
import com.example.cardgame.utils.PlayerCardChanger;
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
                            synchronized (room) {
                                changer.changeCurrentRoleCardFor(player);
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
                            synchronized (room) {
                                changer.changeCurrentMoodCardFor(player);
                                roomService.saveRoom(room);
                            }
                            return player;
                        })
                );
    }
}
