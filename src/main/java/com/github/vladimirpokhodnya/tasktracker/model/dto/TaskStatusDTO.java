package com.github.vladimirpokhodnya.tasktracker.model.dto;

import com.github.vladimirpokhodnya.tasktracker.model.TaskStatus;

public class TaskStatusDTO {
    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

