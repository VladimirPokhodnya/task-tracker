package com.github.vladimirpokhodnya.tasktracker.model.dto;

import com.github.vladimirpokhodnya.tasktracker.model.TaskStatus;

public class TaskDTO {
    private Long id;
    private String title;
    private String description;
    private Long userId;
    private TaskStatus status;

    public TaskDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               ", userId=" + userId +
               ", status=" + status +
               '}';
    }
}
