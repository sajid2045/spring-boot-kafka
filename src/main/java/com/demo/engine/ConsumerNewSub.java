package com.demo.engine;

import com.demo.SpringBootWithKafkaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerNewSub {
    private final Logger logger = LoggerFactory.getLogger(ConsumerNewSub.class);

    @Autowired
    KafkaListenerContainerFactory kafkaListenerContainerFactory;

    @KafkaListener(topics = SpringBootWithKafkaApplication.TOPIC_NEW_SUB, clientIdPrefix="consumer."+SpringBootWithKafkaApplication.TOPIC_NEW_SUB)
    public void newSub(String message) throws IOException {

        logger.info(String.format( ">>>> NEW SUB: %s", message));
        logger.info(String.format( ">>>> NEW SUB: %s", kafkaListenerContainerFactory.getClass()));
        sleep();

    }

    private void sleep() {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
