package com.github.vladimirpokhodnya.tasktracker.kafka;

import com.github.vladimirpokhodnya.tasktracker.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaTaskConsumer {

    private final NotificationService notificationService;

    public KafkaTaskConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "${task.kafka.consumer.topic}", groupId = "${task.kafka.consumer.group-id}")
    public void listen(String message) {
        String[] parts = message.split(":");
        if (parts.length == 2) {
            String taskId = parts[0];
            String newStatus = parts[1];
            notificationService.sendNotification(taskId, newStatus);
        }
    }
}
