package com.github.vladimirpokhodnya.tasktracker.service;

public interface MailSenderService {

    void send(String to, String subject, String body);

}
