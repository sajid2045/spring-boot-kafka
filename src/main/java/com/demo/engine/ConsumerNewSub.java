package com.demo.engine;

import com.demo.SpringBootWithKafkaApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ConsumerNewSub {
    private final Logger logger = LoggerFactory.getLogger(ConsumerNewSub.class);

    @Autowired
    KafkaListenerContainerFactory kafkaListenerContainerFactory;
//
//    /**
//     * AUTO ACK
//     * @param message
//     * @throws IOException
//     */
//    @KafkaListener(topics = SpringBootWithKafkaApplication.TOPIC_NEW_SUB, clientIdPrefix="consumer."+SpringBootWithKafkaApplication.TOPIC_NEW_SUB)
//    public void newSub(String message) throws IOException {
//
//        logger.info(String.format( ">>>> NEW SUB: %s", message));
//        logger.info(String.format( ">>>> NEW SUB: %s", kafkaListenerContainerFactory.getClass()));
//        sleep();
//
//        if(message.equalsIgnoreCase("3")) {
//            throw new ListenerExecutionFailedException("PROCESSING OF MESSAGE FAILED! Hopefully someone will pickitup : " + message);
//        }
//
//        logger.info("<<<< Processing Finished for : " + message);
//
//    }


    /**
     * MANUAL ACK
     *
     * NOTE: need to have spring.kafka.listener.ack-mode=MANUAL_IMMEDIATE set
     *
     * @param message
     * @throws IOException
     */
    @KafkaListener(topics = SpringBootWithKafkaApplication.TOPIC_NEW_SUB,
            clientIdPrefix="consumer."+SpringBootWithKafkaApplication.TOPIC_NEW_SUB, errorHandler = "rollBackOffsetErrorHandler"
    )
    public void newSub(String message, Acknowledgment ack,
                       @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long receivedTS,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws IOException {

        try {
            handleTheMessage(message, partition);
        } catch (Exception e) {
            logger.warn("failed : " + e);
            //SOMETHING WAS WRONG...Zoura timeed out..GOTO SLEEP and retry again
            //throw e; DONOT throw , otherwise we will end up with a recursion message going to queue and coming back in a loop!
        } finally {
            /*
            * Always acknowledge and make sure the message is marked in dynamo as failed
            * only if this server dies totally out of blue and couldn't execute the acknowledge, the other partition handler will pickup the unacknowledged message
            * */
            ack.acknowledge();
        }

    }

    private void handleTheMessage(String message, @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition) {
        logger.info(String.format(">>>> NEW SUB: partition: %s  message: %s ", partition, message));
//        logger.info(String.format(">>>> NEW SUB: %s", kafkaListenerContainerFactory.getClass()));
        sleep();

        if (message.equalsIgnoreCase("3")) {
            throw new ListenerExecutionFailedException("PROCESSING OF MESSAGE FAILED! Hopefully someone will pickitup : " + message);
        }

        logger.info(String.format("<<<< [%s] Processing Finished for : " + message, partition));
    }


    private void sleep() {
        try {
            Thread.sleep(1000 * 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
