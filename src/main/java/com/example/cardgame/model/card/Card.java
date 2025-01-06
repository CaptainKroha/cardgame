package com.example.cardgame.model.card;

import com.example.cardgame.generator.UUIDGenerator;
import org.springframework.data.annotation.Id;

public abstract class Card {
    @Id
    private String id = UUIDGenerator.getNew();
    private String content;

    public Card() {

    }

    public Card(String content) {
        this.content = content;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
