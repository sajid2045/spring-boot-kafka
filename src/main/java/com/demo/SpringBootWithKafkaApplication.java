package com.demo;

import com.demo.engine.ConsumerNewSub;
import org.apache.kafka.clients.consumer.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootWithKafkaApplication {
	private final Logger logger = LoggerFactory.getLogger(SpringBootWithKafkaApplication.class);

	public static final String TOPIC_NEW_SUB = "kayo.platform.billingapi.subscription.test2";
	public static final String TOPIC_RE_SUB = "kayo.platform.billingapi.subscription.resubscribe";


	//TODO need more sophistication
	@Bean
	public ConsumerAwareListenerErrorHandler rollBackOffsetErrorHandler() {
		return new ConsumerAwareListenerErrorHandler() {
			@Override
			public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
				MessageHeaders headers = message.getHeaders();

				Long offset = headers.get(KafkaHeaders.OFFSET, Long.class);

				logger.warn("Could not handle message: rolling back:  " + offset);

				consumer.seek(new org.apache.kafka.common.TopicPartition(
								headers.get(KafkaHeaders.RECEIVED_TOPIC, String.class),
								headers.get(KafkaHeaders.RECEIVED_PARTITION_ID, Integer.class)),
						offset);
				return null;
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithKafkaApplication.class, args);
	}
}
