package com.example.cardgame.model;

import com.example.cardgame.generator.UUIDGenerator;
import org.springframework.data.annotation.Id;

public class Card {
    @Id
    private String id = UUIDGenerator.getNew();
    private CardType type;
    private String content;

    public Card() {

    }

    public Card(CardType type, String content) {
        this.type = type;
        this.content = content;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CardType getType() {
        return type;
    }

    public void setType(CardType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
