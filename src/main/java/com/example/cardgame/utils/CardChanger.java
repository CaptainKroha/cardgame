package com.example.cardgame.utils;

import com.example.cardgame.model.card.Card;

import java.util.List;
import java.util.Random;

public interface CardChanger {
    default <T extends Card> T getRandomCardFromList(List<T> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Card list cannot be empty");
        }
        Random random = new Random();
        return cards.get(random.nextInt(cards.size()));
    }

    default <T extends Card> T getDifferentCard(List<T> cards, T currentCard) {
        if (cards.size() < 2) {
            throw new IllegalArgumentException("Need at least 2 cards to get different one");
        }

        List<T> otherCards = cards.stream()
                .filter(card -> !card.getId().equals(currentCard.getId()))
                .toList();

        return getRandomCardFromList(otherCards);
    }
}
