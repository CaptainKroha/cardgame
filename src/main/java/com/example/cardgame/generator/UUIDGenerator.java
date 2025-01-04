package com.example.cardgame.generator;

import java.util.UUID;

public class UUIDGenerator {
    public static String getNew(){
        return UUID.randomUUID().toString();
    }
}
