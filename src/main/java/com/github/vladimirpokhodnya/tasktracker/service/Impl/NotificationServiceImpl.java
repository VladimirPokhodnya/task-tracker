package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.service.NotificationService;
import com.github.vladimirpokhodnya.tasktracker.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getName());

    private final SendEmailService service;

    public NotificationServiceImpl(SendEmailService service) {
        this.service = service;
    }

    public void sendNotification(String taskId, String newStatus) {
        String message = "Notification for Task ID: " + taskId + " with new status: " + newStatus;
        logger.info(message);
        service.sendEmail(message);
    }

}
