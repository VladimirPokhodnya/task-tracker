package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.Task;

import java.util.List;

public interface TaskService {

    Task createTask(Task task);

    Task getTaskById(Long id);

    Task updateTask(Long id, Task updatedTask);

    void deleteTask(Long id);

    List<Task> getAllTasks();
}
