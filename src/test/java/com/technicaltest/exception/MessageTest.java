package com.technicaltest.exception;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ExtendWith(MockitoExtension.class)
public class MessageTest {

    @Test
    @DisplayName("Test class message")
    void testClassMessage(){
        LocalDateTime timeTest = LocalDateTime.now();
        Message message = new Message(timeTest,"Error", HttpStatus.OK.value());
        Assert.assertEquals(message.getMessage(),"Error");
        Assert.assertEquals(message.getTimestamp(),timeTest.format(DateTimeFormatter.ISO_DATE_TIME));
        Assert.assertEquals(message.getStatus(),HttpStatus.OK.value());
    }
}
