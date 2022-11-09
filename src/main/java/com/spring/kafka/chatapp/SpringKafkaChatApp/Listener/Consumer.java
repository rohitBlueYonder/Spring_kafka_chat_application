package com.spring.kafka.chatapp.SpringKafkaChatApp.Listener;

import com.spring.kafka.chatapp.SpringKafkaChatApp.constants.KafkaConstants;
import com.spring.kafka.chatapp.SpringKafkaChatApp.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Component
public class Consumer {

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = KafkaConstants.TOPIC, groupId = KafkaConstants.GROUP_ID)
    public void consume(Message message){
        System.out.println(message);

        messagingTemplate.convertAndSend("/topic/group", message);
    }


}
