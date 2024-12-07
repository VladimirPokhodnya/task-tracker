package com.github.vladimirpokhodnya.tasktracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class.getName());

    private final MailSenderService service;

    public NotificationService(MailSenderService service) {
        this.service = service;
    }

    @KafkaListener(topics = "task-status-updates", groupId = "task-status-updates")
    public void listen(String message) {
        String[] parts = message.split(":");
        if (parts.length == 2) {
            String taskId = parts[0];
            String newStatus = parts[1];
            logger.info("Notification sent for Task ID: " + taskId + " with new status: " + newStatus);
            sendNotification(taskId, newStatus);
        }
    }


    public void sendNotification(String taskId, String newStatus) {
        service.send(
                "pokhodnya-ngs.ru@yandex.ru",
                "Change of status",
                "Task ID = " + taskId + " has its status updated to " + newStatus
        );

        logger.info("Notification email sent for Task ID: " + taskId + " with new status: " + newStatus);
    }
}
