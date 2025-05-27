package com.example.cardgame.web.socket.exceptions;

public class NotEnoughCardsException extends Exception {

    public NotEnoughCardsException() {
        super("Not enough cards to play");
    }

}
