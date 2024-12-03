package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.Task;
import com.github.vladimirpokhodnya.tasktracker.model.TaskDTO;
import com.github.vladimirpokhodnya.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    final private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = mapToEntity(taskDTO);
        Task createdTask = taskRepository.save(task);
        return mapToDTO(createdTask);
    }

    public Optional<TaskDTO> getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::mapToDTO);
    }

    public Optional<TaskDTO> updateTask(Long id, TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setUserId(id);
                    task.setTitle(taskDTO.getTitle());
                    task.setDescription(taskDTO.getDescription());
                    task.setUserId(taskDTO.getUserId());
                    // ... обновление других полей
                    return mapToDTO(taskRepository.save(task));
                });
    }


    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private TaskDTO mapToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUserId(task.getUserId());
        return taskDTO;
    }

    private Task mapToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setUserId(taskDTO.getUserId());
        return task;
    }
}
