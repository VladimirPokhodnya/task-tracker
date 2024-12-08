package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.service.MailSenderService;
import com.github.vladimirpokhodnya.tasktracker.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getName());

    @Value("${spring.email.from-address}")
    private String mailFrom;

    private final MailSenderService service;

    public NotificationServiceImpl(MailSenderService service) {
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
