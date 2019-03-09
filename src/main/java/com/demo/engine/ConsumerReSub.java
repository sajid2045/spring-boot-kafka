package com.demo.engine;

import com.demo.SpringBootWithKafkaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerReSub {
    private final Logger logger = LoggerFactory.getLogger(ConsumerReSub.class);

    @KafkaListener(topics = SpringBootWithKafkaApplication.TOPIC_RE_SUB, clientIdPrefix = "consumer." + SpringBootWithKafkaApplication.TOPIC_RE_SUB)
    public void reSub(String message) throws IOException {
        logger.info(String.format("@@@@ RE SUB: %s", message));
    }
}
