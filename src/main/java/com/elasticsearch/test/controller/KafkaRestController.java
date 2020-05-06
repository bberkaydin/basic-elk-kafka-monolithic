package com.elasticsearch.test.controller;

import com.elasticsearch.test.model.User;
import com.elasticsearch.test.service.UserService;
import com.elasticsearch.test.stream.producer.RestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class KafkaRestController {
    private final RestProducer producer;

    @Autowired
    UserService service;

    @Autowired
    public KafkaRestController(RestProducer producer) {
        this.producer = producer;
    }

    @GetMapping("/api")
    public String api(){
        return "api";
    }

    @PostMapping(value = "/produce-all-es-data")
    public String sendMessageToKafkaTopic(){
        String message = "";
        Iterable<User> users = service.findAll();
        for(User u : users){
            message += u.toString() + "\n";
        }
        this.producer.sendMessage(message);
        return message;
    }
}
