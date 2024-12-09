package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.service.SendEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private static final Logger logger = LoggerFactory.getLogger(SendEmailServiceImpl.class.getName());

    @Value("${email.from-address}")
    private String from;

    @Value("${email.to-default}")
    private String to;

    private final JavaMailSender mailSender;

    public SendEmailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String msg) {
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
