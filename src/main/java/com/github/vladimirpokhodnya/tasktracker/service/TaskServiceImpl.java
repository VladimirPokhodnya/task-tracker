package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.Task;
import com.github.vladimirpokhodnya.tasktracker.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    final private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public Task updateTask(Long id, Task updatedTask) {
        if (!taskRepository.existsById(id)) {
            logger.error("Отсутствует задание с id {}", id);
            return null;
        }
        updatedTask.setId(id);
        return taskRepository.save(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
