package com.example.cardgame.web.socket.service;

import com.example.cardgame.model.card.*;
import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.repository.RoomRepository;
import com.example.cardgame.utils.PlayerCardChanger;
import com.example.cardgame.web.socket.exception.RoomNotFoundException;
import com.example.cardgame.web.socket.messages.WebSocketMessage;
import com.example.cardgame.web.socket.model.GameStartRequest;
import com.example.cardgame.web.socket.messages.bodies.GameStartMessageBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private RoomRepository roomRepository;

    public WebSocketMessage startGame(GameStartRequest request) throws RoomNotFoundException {
        Optional<Room> optionalRoom = roomRepository.findById(request.getRoomId());

        if (optionalRoom.isEmpty()) {
            throw new RoomNotFoundException();
        }

        Room room = optionalRoom.get();
        room.setGameStarted(true);

        if (!room.getSituationCards().isEmpty()) {
            SituationCard situationCard = room.getSituationCards().get(0);
            room.setSituationCard(situationCard);
        }

        PlayerCardChanger playerCardChanger = new PlayerCardChanger(room);

        // Распределение карт ролей, настроения и активных карт для каждого игрока
        List<Player> players = room.getPlayers();
        List<ActionCard> actionCards = room.getActionCards();

        for (Player player : players) {
            playerCardChanger.setRoleCardFor(player);
            playerCardChanger.setMoodCardFor(player);

            List<ActionCard> playerActionCards = new ArrayList<>();
            for (int i = 0; i < room.getCardsPerPlayer() && !actionCards.isEmpty(); i++) {
                playerActionCards.add(actionCards.remove(0));  // Выбираем и удаляем первые 6 активных карт
            }
            player.setActionCards(playerActionCards);
        }

        roomRepository.save(room);
        return WebSocketMessage.gameStarted(GameStartMessageBody.get(room));
    }

    public WebSocketMessage stopGame(String roomId) throws RoomNotFoundException {
        roomRepository.deleteById(roomId);
        return WebSocketMessage.gameStopped();
    }
}
