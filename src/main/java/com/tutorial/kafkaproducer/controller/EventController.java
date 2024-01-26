package com.tutorial.kafkaproducer.controller;

import com.tutorial.kafkaproducer.service.KafkaMessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class EventController {
    @Autowired
    private KafkaMessagePublisher publisher;

    @PostMapping("/publish")
    public ResponseEntity<?> publishMessage(@RequestBody String message){
        try{
            publisher.sendMessageToTopic(message);
            return ResponseEntity.ok("send message successfully ..");

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("can not send message" + exception.getMessage());
        }
    }
}
