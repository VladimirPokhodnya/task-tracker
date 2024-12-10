package com.github.vladimirpokhodnya.tasktracker.service;

public interface NotificationService {
    void sendNotification(String taskId, String newStatus);
}
