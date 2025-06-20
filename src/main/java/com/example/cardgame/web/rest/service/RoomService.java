package com.example.cardgame.web.rest.service;

import com.example.cardgame.model.CreateRoomRequest;
import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.model.card.ActionCard;
import com.example.cardgame.model.card.MoodCard;
import com.example.cardgame.model.card.RoleCard;
import com.example.cardgame.model.card.SituationCard;
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

    public void saveRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
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
