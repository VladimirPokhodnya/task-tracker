package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.TaskDTO;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    Optional<TaskDTO> getTaskById(Long id);

    Optional<TaskDTO> updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    List<TaskDTO> getAllTasks();
}
