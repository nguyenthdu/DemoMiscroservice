package com.example.notification_service.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
	@KafkaListener(topics = "employeeCreatedTopic", groupId = "notification-group")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}
}
