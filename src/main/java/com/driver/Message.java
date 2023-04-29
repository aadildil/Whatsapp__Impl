package com.driver;

import java.time.LocalDate;

public class Message {
    private int id;
    private String content;
    private LocalDate timestamp;

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
        this.timestamp = LocalDate.now();
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }
}
