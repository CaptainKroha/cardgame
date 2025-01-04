package com.example.cardgame.controller;

import com.example.cardgame.model.Card;
import com.example.cardgame.model.CardType;
import com.example.cardgame.model.Player;
import com.example.cardgame.model.Room;
import com.example.cardgame.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public CreateRoomResponse createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        Room room = new Room();

        Player creator = new Player(createRoomRequest.getCreatorLogin());
        room.setPlayers(List.of(creator));

        room.setCreatorId(creator.getPlayerId());
        room.setCardsPerPlayer(createRoomRequest.getCardsPerPlayer());

        List<Card> situationCardObjects = createRoomRequest.getSituationCards().stream()
                .map(content -> new Card(CardType.SITUATION, content))
                .toList();

        List<Card> roleCardObjects = createRoomRequest.getRoleCards().stream()
                .map(content -> new Card(CardType.ROLE, content))
                .toList();

        List<Card> moodCardObjects = createRoomRequest.getMoodCards().stream()
                .map(content -> new Card(CardType.MOOD, content))
                .toList();

        List<Card> actionCardObjects = createRoomRequest.getActionCards().stream()
                .map(content -> new Card(CardType.ACTION, content))
                .toList();

        room.setSituationCards(situationCardObjects);
        room.setRoleCards(roleCardObjects);
        room.setMoodCards(moodCardObjects);
        room.setActionCards(actionCardObjects);

        Room createdRoom = roomService.createRoom(room);
        return new CreateRoomResponse(createdRoom.getRoomId(), createdRoom.getSimpleRoomId());
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomId) {
        return roomService.getRoomById(roomId)
                .map(room -> ResponseEntity.ok().body(room))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{roomId}")
    public Room updateRoom(@PathVariable String roomId, @RequestBody Room room) {
        room.setRoomId(roomId);
        return roomService.updateRoom(room);
    }

    @DeleteMapping("/{roomId}")
    public void deleteRoom(@PathVariable String roomId) {
        roomService.deleteRoom(roomId);
    }

    @PostMapping("/simple/{simpleRoomId}/players")
    public ResponseEntity<Player> addPlayerToRoom(@PathVariable String simpleRoomId, @RequestBody String login) {
        Optional<Player> player = roomService.addPlayerToRoom(simpleRoomId, login);
        return player.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/simple/{simpleRoomId}")
    public ResponseEntity<String> getRoomIdBySimpleRoomId(@PathVariable String simpleRoomId) {
        String roomId = roomService.getRoomIdBySimpleRoomId(simpleRoomId);
        if(!roomId.isEmpty()) {
            return ResponseEntity.ok().body(roomId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{roomId}/start")
    public ResponseEntity<Void> startGame(@PathVariable String roomId) {
        boolean isStarted = roomService.startGame(roomId);
        if (isStarted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{roomId}/players")
    public ResponseEntity<Player> getPlayer(@RequestParam String playerId) {
        return roomService.getPlayerById(playerId)
                .map(player -> ResponseEntity.ok().body(player))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
