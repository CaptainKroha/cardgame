package com.example.cardgame.controller;

import com.example.cardgame.model.*;
import com.example.cardgame.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public String createRoom(@RequestBody CreateRoomRequest createRoomRequest) {
        return roomService.createRoom(createRoomRequest);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomId) {
        return roomService.getRoomById(roomId)
                .map(room -> ResponseEntity.ok().body(room))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{roomId}/players")
    public ResponseEntity<Player> getPlayer(@RequestParam String playerId) {
        return roomService.getPlayerById(playerId)
                .map(player -> ResponseEntity.ok().body(player))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{roomId}/players")
    public ResponseEntity<Player> addPlayerToRoom(@PathVariable String roomId, @RequestBody String login) {
        Optional<Player> player = roomService.addPlayerToRoom(roomId, login);
        return player.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
