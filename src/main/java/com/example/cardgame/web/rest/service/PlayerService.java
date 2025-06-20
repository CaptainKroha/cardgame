package com.example.cardgame.web.rest.service;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.web.rest.utils.PlayerSearchResult;
import com.example.cardgame.web.socket.exceptions.RoomNotFoundException;
import com.example.cardgame.web.socket.messages.WebSocketMessage;
import com.example.cardgame.web.socket.messages.bodies.PlayerEnterMessageBody;
import com.example.cardgame.web.socket.messages.bodies.PlayerLeftMessageBody;
import com.example.cardgame.web.socket.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private RoomService roomService;

    @Autowired
    private NotificationService notificationService;

    public Player addPlayerToRoom(String roomId, String login) throws RoomNotFoundException {

        Room room = roomService.getRoomById(roomId)
                .orElseThrow(RoomNotFoundException::new);
        Player player = new Player(login);
        room.getPlayers().add(player);

        roomService.saveRoom(room);

        PlayerEnterMessageBody messageBody = new PlayerEnterMessageBody(room.getPlayers().size(), player.getLogin());
        notificationService.notifyPlayers(roomId, WebSocketMessage.playerEntered(messageBody));

        return player;
    }

    public PlayerSearchResult getPlayerById(String roomId, String playerId) {
        Optional<Room> roomOpt = roomService.getRoomById(roomId);
        if (roomOpt.isEmpty()) {
            return PlayerSearchResult.roomNotFound();
        }

        Room room = roomOpt.get();
        Optional<Player> playerOpt = room.findPlayerById(playerId);

        return playerOpt
                .map(PlayerSearchResult::success)
                .orElse(PlayerSearchResult.playerNotFound());
    }

    public void dropPlayerFromRoom(String roomId, String playerId) throws RoomNotFoundException {
        Room room = roomService.getRoomById(roomId)
                .orElseThrow(RoomNotFoundException::new);
        Player player = room.findPlayerById(playerId)
                .orElseThrow(IllegalArgumentException::new);
        room.getPlayers().remove(player);

        if(room.getIsGameStarted()) {
            returnPlayersCardsToDeck(room, player);
        }

        roomService.saveRoom(room);

        PlayerLeftMessageBody messageBody = new PlayerLeftMessageBody(room.getPlayers().size(), player.getLogin());
        notificationService.notifyPlayers(roomId, WebSocketMessage.playerLeft(messageBody));

    }

    public void returnPlayersCardsToDeck(Room room, Player player) {
        room.getRoleCards().add(player.getRoleCard());
        room.getMoodCards().add(player.getMoodCard());
        room.getDroppedActionCards().addAll(player.getActionCards());
    }
}
