package com.example.cardgame.web.rest.controller;

import com.example.cardgame.model.Player;
import com.example.cardgame.web.rest.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rooms/{roomId}/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayer(@PathVariable String roomId, @PathVariable String playerId) {
        return playerService.getPlayerById(roomId, playerId)
                .map(player -> ResponseEntity.ok().body(player))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Player> addPlayerToRoom(@PathVariable String roomId, @RequestBody String login) {
        Optional<Player> player = playerService.addPlayerToRoom(roomId, login);
        return player.map(value -> ResponseEntity.ok().body(value))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{playerId}")
    public void dropPlayerFromRoom(@PathVariable String roomId, @PathVariable String playerId) {
        playerService.dropPlayerFromRoom(roomId, playerId);
    }
}
