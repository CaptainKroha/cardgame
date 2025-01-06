package com.example.cardgame.websocket.service;

import com.example.cardgame.model.card.*;
import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.repository.RoomRepository;
import com.example.cardgame.websocket.exception.GameStartException;
import com.example.cardgame.websocket.model.GameStartRequest;
import com.example.cardgame.websocket.model.GameStartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {

    @Autowired
    private RoomRepository roomRepository;

    public GameStartResponse startGame(GameStartRequest request) throws GameStartException {
        Optional<Room> optionalRoom = roomRepository.findById(request.getRoomId());

        if (optionalRoom.isEmpty()) {
            throw new GameStartException("Room was not found");
        }

        Room room = optionalRoom.get();
        room.setGameStarted(true);

        if (!room.getSituationCards().isEmpty()) {
            SituationCard situationCard = room.getSituationCards().get(0);  // Например, просто выбираем первую карту
            room.setSituationCard(situationCard);
        }

        // Распределение карт ролей, настроения и активных карт для каждого игрока
        List<Player> players = room.getPlayers();
        List<RoleCard> roleCards = new ArrayList<>(room.getRoleCards());
        List<MoodCard> moodCards = new ArrayList<>(room.getMoodCards());
        List<ActionCard> actionCards = new ArrayList<>(room.getActionCards());

        for (Player player : players) {
            if (!roleCards.isEmpty()) {
                player.setRoleCard(roleCards.remove(0));  // Выбираем и удаляем первую карту роли
            }
            if (!moodCards.isEmpty()) {
                player.setMoodCard(moodCards.remove(0));  // Выбираем и удаляем первую карту настроения
            }

            List<ActionCard> playerActionCards = new ArrayList<>();
            for (int i = 0; i < room.getCardsPerPlayer() && !actionCards.isEmpty(); i++) {
                playerActionCards.add(actionCards.remove(0));  // Выбираем и удаляем первые 6 активных карт
            }
            player.setActionCards(playerActionCards);
        }

        roomRepository.save(room);
        return GameStartResponse.success(room);
    }

}
