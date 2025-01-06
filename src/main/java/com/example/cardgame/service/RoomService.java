package com.example.cardgame.service;

import com.example.cardgame.model.*;
import com.example.cardgame.model.card.*;
import com.example.cardgame.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public String createRoom(CreateRoomRequest createRoomRequest) {

        Room room = new Room();

        Player creator = new Player(createRoomRequest.getCreatorLogin());
        room.getPlayers().add(creator);

        room.setCreatorId(creator.getPlayerId());
        room.setCardsPerPlayer(createRoomRequest.getCardsPerPlayer());

        List<SituationCard> situationCardObjects = createRoomRequest.getSituationCards().stream()
                .map(SituationCard::new).toList();

        List<RoleCard> roleCardObjects = createRoomRequest.getRoleCards().stream()
                .map(RoleCard::new).toList();

        List<MoodCard> moodCardObjects = createRoomRequest.getMoodCards().stream()
                .map(MoodCard::new).toList();

        List<ActionCard> actionCardObjects = createRoomRequest.getActionCards().stream()
                .map(ActionCard::new).toList();

        room.setSituationCards(situationCardObjects);
        room.setRoleCards(roleCardObjects);
        room.setMoodCards(moodCardObjects);
        room.setActionCards(actionCardObjects);
        room.setRoomId(generateUniqueRoomId());
        return roomRepository.save(room).getRoomId();
    }

    public Optional<Room> getRoomById(String roomId) {
        return roomRepository.findById(roomId);
    }

    public Optional<Player> addPlayerToRoom(String roomId, String login) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
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

    private String generateSimpleRoomId() {
        Random random = new Random();
        int number = random.nextInt(90000) + 10000; // Генерация случайного 5-значного числа
        return String.valueOf(number);
    }

    private String generateUniqueRoomId() {
        String roomId;
        do {
            roomId = generateSimpleRoomId();
        } while (roomRepository.existsById(roomId));
        return roomId;
    }

}
