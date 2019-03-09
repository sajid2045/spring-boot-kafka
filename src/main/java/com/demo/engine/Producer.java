package com.demo.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.demo.SpringBootWithKafkaApplication.TOPIC_NEW_SUB;
import static com.demo.SpringBootWithKafkaApplication.TOPIC_RE_SUB;

@Service
public class Producer {

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Value("${KAFKA_PRODUCER:false}")
    private boolean shouldProduce;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topicId, String message) {
        this.kafkaTemplate.send(topicId, message);
    }

    @Scheduled(fixedRate = 1000)
    public void putTestMessage() {
        if (!shouldProduce) {
            return;
        }

        logger.info("put some messages to topic");

        sendMessage(TOPIC_NEW_SUB, "THIS IS A MESSAGE " + UUID.randomUUID());
        sendMessage(TOPIC_RE_SUB, "THIS IS A MESSAGE " + UUID.randomUUID());
    }
}
