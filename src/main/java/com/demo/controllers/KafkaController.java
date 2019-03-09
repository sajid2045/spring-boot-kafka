package com.demo.controllers;

import com.demo.SpringBootWithKafkaApplication;
import com.demo.engine.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    private final Producer producer;

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/subscription/new")
    public void newSub(@RequestParam("message") String message) {
        this.producer.sendMessage(SpringBootWithKafkaApplication.TOPIC_NEW_SUB, message);
    }

    @PostMapping(value = "/subscription/resubscribe")
    public void reSub(@RequestParam("message") String message) {
        this.producer.sendMessage(SpringBootWithKafkaApplication.TOPIC_RE_SUB, message);
    }
}
