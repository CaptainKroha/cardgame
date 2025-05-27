package com.example.cardgame.web.socket.exceptions;

public record ExceptionDto(
    ExceptionTypes type,
    String message,
    String timestamp
) { }
