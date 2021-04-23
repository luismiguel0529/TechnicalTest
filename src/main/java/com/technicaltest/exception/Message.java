package com.technicaltest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {

    private final LocalDateTime timestamp;
    private final String message;
    private int status;

    public Message(LocalDateTime timestamp, String message, int status) {
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
