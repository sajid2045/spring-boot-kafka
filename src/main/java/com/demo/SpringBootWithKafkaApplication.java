package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootWithKafkaApplication {
	public static final String TOPIC_NEW_SUB = "kayo.platform.billingapi.subscription.test2";
	public static final String TOPIC_RE_SUB = "kayo.platform.billingapi.subscription.resubscribe";

	public static void main(String[] args) {
		SpringApplication.run(SpringBootWithKafkaApplication.class, args);
	}
}
