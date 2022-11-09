package com.spring.kafka.chatapp.SpringKafkaChatApp.controller;

import com.spring.kafka.chatapp.SpringKafkaChatApp.constants.KafkaConstants;
import com.spring.kafka.chatapp.SpringKafkaChatApp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@RestController
public class MessageController {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @PostMapping(value = "/api/send", consumes = "application/json", produces = "application/json")
    public void send(@RequestBody Message message){
        message.setTimestamp(LocalDateTime.now().toString());
        try{
            kafkaTemplate.send(KafkaConstants.TOPIC, message).get();
        }
        catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }

        catch (Exception e){
            System.out.println(e);
        }
    }


    // Web-Socket APIs
    @MessageMapping("/send-messagge")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message){
        return message;
    }


    @MessageMapping("/new-user")
    @SendTo("/topic/group")
    public Message addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor){
        // trying to add user for web-socket API session
        headerAccessor.getSessionAttributes().put("userName", message.getSender());
        return message;
    }

}
