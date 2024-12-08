package com.github.vladimirpokhodnya.tasktracker.model.dto;

import com.github.vladimirpokhodnya.tasktracker.model.TaskStatus;

public record TaskStatusDTO(Long id, TaskStatus status) {}

