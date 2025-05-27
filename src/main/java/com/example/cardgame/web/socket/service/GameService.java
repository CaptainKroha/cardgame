package com.example.cardgame.web.socket.service;

import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.repository.RoomRepository;
import com.example.cardgame.utils.PlayerCardChanger;
import com.example.cardgame.utils.RoomCardChanger;
import com.example.cardgame.web.socket.exceptions.NotEnoughCardsException;
import com.example.cardgame.web.socket.exceptions.RoomNotFoundException;
import com.example.cardgame.web.socket.messages.WebSocketMessage;
import com.example.cardgame.web.socket.messages.bodies.GameStartMessageBody;
import com.example.cardgame.web.socket.model.GameStartRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GameService {

    @Autowired
    private RoomRepository roomRepository;

    public WebSocketMessage startGame(GameStartRequest request) throws RoomNotFoundException, NotEnoughCardsException {
        Optional<Room> optionalRoom = roomRepository.findById(request.getRoomId());

        if (optionalRoom.isEmpty()) {
            throw new RoomNotFoundException();
        }

        Room room = optionalRoom.get();
        room.setIsGameStarted(true);

        RoomCardChanger roomCardChanger = new RoomCardChanger(room);
        roomCardChanger.setSituationCard();

        PlayerCardChanger playerCardChanger = new PlayerCardChanger(room);

        List<Player> players = room.getPlayers();
        try{
            for (Player player : players) {
                playerCardChanger.setRoleCardFor(player);
                playerCardChanger.setMoodCardFor(player);
                playerCardChanger.setActionCardsFor(player);
            }
        } catch (IllegalArgumentException e){
            throw new NotEnoughCardsException();
        }

        roomRepository.save(room);
        return WebSocketMessage.gameStarted(GameStartMessageBody.get(room));
    }

    public WebSocketMessage stopGame(String roomId) throws RoomNotFoundException {
        roomRepository.deleteById(roomId);
        return WebSocketMessage.gameStopped();
    }
}
