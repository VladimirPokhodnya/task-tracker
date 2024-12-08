package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.service.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderServiceImpl implements MailSenderService {
    private static final Logger logger = LoggerFactory.getLogger(MailSenderServiceImpl.class.getName());

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender mailSender;

    public MailSenderServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String to, String subject, String body) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(body);
        mailMessage.setFrom(from);

        try {
            mailSender.send(mailMessage);
        } catch (Exception e) {
            logger.error("Error while sending mail", e);
        }
    }

}
