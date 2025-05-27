package com.example.cardgame.web.rest.controller;

import com.example.cardgame.model.Player;
import com.example.cardgame.web.rest.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rooms/{roomId}")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/players/{playerId}/cards")
    public ResponseEntity<Player> changeRoleCard(
            @PathVariable String roomId,
            @PathVariable String playerId) {
        return cardService.changeRoleCard(roomId, playerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/players/{playerId}/cards/mood/change")
    public ResponseEntity<Player> changeMoodCard(
            @PathVariable String roomId,
            @PathVariable String playerId ) {
        return cardService.changeMoodCard(roomId, playerId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("cards/situation/change")
    public ResponseEntity<Void> changeSituationCard(
            @PathVariable String roomId) {
        return cardService.changeSituationCard(roomId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/players/{playerId}/cards/action/drop/{cardId}")
    public ResponseEntity<Void> dropActionCard(
            @PathVariable String roomId,
            @PathVariable String playerId,
            @PathVariable String cardId) {
        return cardService.dropActionCard(roomId, playerId, cardId)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

}
