package com.github.vladimirpokhodnya.tasktracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class.getName());

    @Value("${spring.email.from-address}")
    private String mailFrom;

    private final MailSenderService service;

    public NotificationService(MailSenderService service) {
        this.service = service;
    }

    public void sendNotification(String taskId, String newStatus) {
        service.send(
                mailFrom,
                "Change of status",
                "Task ID = " + taskId + " has its status updated to " + newStatus
        );

        logger.info("Notification email sent for Task ID: " + taskId + " with new status: " + newStatus);
    }
}
