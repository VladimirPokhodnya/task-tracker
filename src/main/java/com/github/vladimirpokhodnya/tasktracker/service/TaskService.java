package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.TaskDTO;

import java.util.List;

public interface TaskService {

    TaskDTO createTask(TaskDTO taskDTO);

    TaskDTO getTaskById(Long id);

    TaskDTO updateTask(Long id, TaskDTO taskDTO);

    void deleteTask(Long id);

    List<TaskDTO> getAllTasks();
}
