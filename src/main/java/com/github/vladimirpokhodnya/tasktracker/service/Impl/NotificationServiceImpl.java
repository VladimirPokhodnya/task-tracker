package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {
    private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class.getName());

    @Value("${spring.mail.username}")
    private String to;

    @Value("${email.from-address}")
    private String from;

    private final JavaMailSender mailSender;

    public NotificationServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendNotification(String taskId, String newStatus) {
        logger.info("Notification for Task ID: " + taskId + " with new status: " + newStatus);
        sendEmail("Notification for Task ID: " + taskId + " with new status: " + newStatus);
    }


    private void sendEmail(String msg) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(to);
            message.setSubject(msg);
            message.setText(msg);

            mailSender.send(message);
            logger.info("Email sent to: <" + to + ">");
        } catch (Exception e) {
            logger.error("Error while sending mail", e);
        }
    }
}
