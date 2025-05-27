package com.example.cardgame.web.socket.exceptions;

import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.security.Principal;
import java.time.LocalDateTime;

@ControllerAdvice
public class WebSocketExceptionHandler {

    @MessageExceptionHandler(NotEnoughCardsException.class)
    @SendToUser("/queue/errors")
    public ExceptionDto handleException(NotEnoughCardsException ex, Principal principal) {
        return new ExceptionDto(
                ExceptionTypes.NOT_ENOUGH_CARDS,
                ex.getMessage(),
                LocalDateTime.now().toString()
        );
    }
}
