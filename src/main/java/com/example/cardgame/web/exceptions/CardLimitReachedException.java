package com.example.cardgame.web.exceptions;

public class CardLimitReachedException extends RuntimeException {
    public CardLimitReachedException(String message) {
        super(message);
    }
}
