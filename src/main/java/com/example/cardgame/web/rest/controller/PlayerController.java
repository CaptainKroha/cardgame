package com.example.cardgame.web.rest.controller;

import com.example.cardgame.model.Player;
import com.example.cardgame.web.rest.service.PlayerService;
import com.example.cardgame.web.rest.utils.PlayerSearchResult;
import com.example.cardgame.web.socket.exceptions.RoomNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rooms/{roomId}/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{playerId}")
    public PlayerSearchResult getPlayer(@PathVariable String roomId, @PathVariable String playerId) {
        return playerService.getPlayerById(roomId, playerId);
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(
            @PathVariable String roomId,
            @RequestBody String login
    ) {
        try{
            Player player = playerService.addPlayerToRoom(roomId, login);
            return ResponseEntity.ok(player);
        } catch (RoomNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{playerId}")
    public void dropPlayerFromRoom(@PathVariable String roomId, @PathVariable String playerId) throws RoomNotFoundException {
        playerService.dropPlayerFromRoom(roomId, playerId);
    }
}
