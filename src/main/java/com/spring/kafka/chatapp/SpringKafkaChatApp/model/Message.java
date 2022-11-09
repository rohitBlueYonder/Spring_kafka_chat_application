package com.spring.kafka.chatapp.SpringKafkaChatApp.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {

    private String sender;
    private String text;
    private String timestamp;

}
