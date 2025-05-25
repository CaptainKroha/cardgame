package com.example.cardgame.web.rest.controller;

import com.example.cardgame.model.Player;
import com.example.cardgame.web.rest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms/{roomId}/players/{playerId}/cards")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/role/change")
    public ResponseEntity<Player> changeRoleCard(
            @PathVariable String roomId,
            @PathVariable String playerId) {
        return cardService.changeRoleCard(roomId, playerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/mood/change")
    public ResponseEntity<Player> changeMoodCard(
            @PathVariable String roomId,
            @PathVariable String playerId ) {
        return cardService.changeMoodCard(roomId, playerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
