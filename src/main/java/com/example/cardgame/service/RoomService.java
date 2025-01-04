package com.example.cardgame.service;

import com.example.cardgame.model.Card;
import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public Room createRoom(Room room) {
        room.setSimpleRoomId(generateUniqueSimpleRoomId());
        return roomRepository.save(room);
    }

    public Optional<Room> getRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }

    public Room updateRoom(Room room) {
        return roomRepository.save(room);
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }

    public String getRoomIdBySimpleRoomId(String simpleRoomId) {
        Optional<Room> optionalRoom = roomRepository.findBySimpleRoomId(simpleRoomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            return room.getRoomId();
        }
        return "";
    }

    public Optional<Player> addPlayerToRoom(String simpleRoomId, String login) {
        Optional<Room> optionalRoom = roomRepository.findBySimpleRoomId(simpleRoomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            Player player = new Player(login);
            room.getPlayers().add(player);
            roomRepository.save(room);
            return Optional.of(player);
        }
        return Optional.empty();
    }

    public Optional<Player> getPlayerById(String playerId) {
        for(Room room : roomRepository.findAll()) {
            Optional<Player> player = room.getPlayers().stream()
                    .filter(p -> p.getPlayerId().equals(playerId))
                    .findFirst();
            if(player.isPresent()){
                return player;
            }
        }
        return Optional.empty();
    }

    public boolean startGame(String roomId) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);

        if (optionalRoom.isEmpty()) {
            return false;
        }

        Room room = optionalRoom.get();
        room.setGameStarted(true);

        if (!room.getSituationCards().isEmpty()) {
            Card situationCard = room.getSituationCards().get(0);  // Например, просто выбираем первую карту
            room.setSituationCard(situationCard);
        }

        // Распределение карт ролей, настроения и активных карт для каждого игрока
        List<Player> players = room.getPlayers();
        List<Card> roleCards = new ArrayList<>(room.getRoleCards());
        List<Card> moodCards = new ArrayList<>(room.getMoodCards());
        List<Card> actionCards = new ArrayList<>(room.getActionCards());

        for (Player player : players) {
            if (!roleCards.isEmpty()) {
                player.setRoleCard(roleCards.remove(0));  // Выбираем и удаляем первую карту роли
            }
            if (!moodCards.isEmpty()) {
                player.setMoodCard(moodCards.remove(0));  // Выбираем и удаляем первую карту настроения
            }

            List<Card> playerActionCards = new ArrayList<>();
            for (int i = 0; i < room.getCardsPerPlayer() && !actionCards.isEmpty(); i++) {
                playerActionCards.add(actionCards.remove(0));  // Выбираем и удаляем первые 6 активных карт
            }
            player.setActionCards(playerActionCards);
        }


        roomRepository.save(room);
        // Логика для уведомления игроков, например, через WebSocket
        return true;
    }

    private String generateSimpleRoomId() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000; // Генерация случайного 5-значного числа
        return String.valueOf(number);
    }

    private String generateUniqueSimpleRoomId() {
        String simpleRoomId;
        do {
            simpleRoomId = generateSimpleRoomId();
        } while (roomRepository.existsBySimpleRoomId(simpleRoomId));
        return simpleRoomId;
    }

}
