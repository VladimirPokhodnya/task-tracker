package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.Task;
import com.github.vladimirpokhodnya.tasktracker.model.TaskDTO;
import com.github.vladimirpokhodnya.tasktracker.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    final private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = mapToEntity(taskDTO);
        Task createdTask = taskRepository.save(task);
        return mapToDTO(createdTask);
    }

    public TaskDTO getTaskById(Long id) {
        return taskRepository.findById(id)
                .map(this::mapToDTO)
                .orElse(null);
    }

    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        if (!taskRepository.existsById(id)) {
            return null;
        }
        Task task = mapToEntity(taskDTO);
        task.setId(id);
        Task updatedTask = taskRepository.save(task);
        return mapToDTO(updatedTask);
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
