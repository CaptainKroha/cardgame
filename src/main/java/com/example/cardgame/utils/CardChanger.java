package com.example.cardgame.utils;

import com.example.cardgame.model.card.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface CardChanger {
    default <T extends Card> T getRandomCardFromList(List<T> cards) {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Card list cannot be empty");
        }
        Random random = new Random();
        T newCard = cards.get(random.nextInt(cards.size()));
        cards.remove(newCard);
        return newCard;
    }

    default <T extends Card> List<T> getRandomCardsSetFromList(List<T> cards, int count) {
        List<T> cardsSet = new ArrayList<>();
        for(int i = 1; i <= count; i++) {
            T card = getRandomCardFromList(cards);
            cardsSet.add(card);
        }
        return cardsSet;
    }

    default <T extends Card> T swapCurrentCard(List<T> cards, T currentCard) {
        T newCard = getRandomCardFromList(cards);
        cards.add(currentCard);
        return newCard;
    }
}
