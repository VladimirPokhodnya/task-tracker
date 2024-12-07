package com.github.vladimirpokhodnya.tasktracker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class.getName());

    private final JavaMailSender emailSender;

    public NotificationService(JavaMailSender emailSender) {
        this.emailSender = emailSender;
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

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("pokhodnya-ngs.ru@yandex.ru");
        mailMessage.setSubject("Task Status Update");
        mailMessage.setText("Task ID: " + taskId + "\nNew Status: " + newStatus);


        try {
            emailSender.send(mailMessage);
            logger.info("Email sent successfully to: " + "Task ID: " + taskId + "\nNew Status: " + newStatus);
        } catch (MailSendException e) {
            logger.error("Failed to send email to: " + "pokhodnya-ngs.ru@yandex.ru" + ". Error message: " + e.getMessage());
        }

        logger.info("Notification email sent for Task ID: " + taskId + " with new status: " + newStatus);
    }
}
