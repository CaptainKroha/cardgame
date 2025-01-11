package com.example.cardgame.web.rest.service;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private RoomService roomService;

    public Optional<Player> addPlayerToRoom(String roomId, String login) {
        Optional<Room> optionalRoom = roomService.getRoomById(roomId);
        if(optionalRoom.isEmpty()) {
            return Optional.empty();
        }
        Room room = optionalRoom.get();
        Player player = new Player(login);
        room.getPlayers().add(player);
        roomService.saveRoom(room);
        return Optional.of(player);
    }

    public Optional<Player> getPlayerById(String roomId, String playerId) {
        Optional<Room> optionalRoom = roomService.getRoomById(roomId);
        if(optionalRoom.isEmpty()) {
            return Optional.empty();
        }
        Room room = optionalRoom.get();
        return room.getPlayers().stream()
                .filter(p -> p.getPlayerId().equals(playerId))
                .findFirst();
    }

    public void dropPlayerFromRoom(String roomId, String playerId) {
        Optional<Room> optionalRoom = roomService.getRoomById(roomId);
        if(optionalRoom.isEmpty()) {
            return;
        }
        Room room = optionalRoom.get();
        room.getPlayers().removeIf(player -> player.getPlayerId().equals(playerId));
        roomService.saveRoom(room);
    }
}
