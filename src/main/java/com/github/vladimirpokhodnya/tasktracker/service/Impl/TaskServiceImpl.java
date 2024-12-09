package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.model.Task;
import com.github.vladimirpokhodnya.tasktracker.model.TaskStatus;
import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskDTO;
import com.github.vladimirpokhodnya.tasktracker.repository.TaskRepository;
import com.github.vladimirpokhodnya.tasktracker.service.KafkaTaskProducerService;
import com.github.vladimirpokhodnya.tasktracker.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final KafkaTaskProducerService kafkaTaskProducerService;

    public TaskServiceImpl(TaskRepository taskRepository, KafkaTaskProducerService kafkaTaskProducerService) {
        this.taskRepository = taskRepository;
        this.kafkaTaskProducerService = kafkaTaskProducerService;
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
                    task.setStatus(taskDTO.getStatus());
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

    public Optional<TaskDTO> updateStatus(Long taskId, TaskStatus newStatus) {

        Optional<TaskDTO> updatedTask = taskRepository.findById(taskId)
                .map(task -> {
                    task.setStatus(newStatus);
                    return mapToDTO(taskRepository.save(task));
                });

        updatedTask.ifPresent(task -> kafkaTaskProducerService.sent(updatedTask.get()));

        return updatedTask;
    }


    private TaskDTO mapToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setUserId(task.getUserId());
        taskDTO.setStatus(task.getStatus());
        return taskDTO;
    }

    private Task mapToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setUserId(taskDTO.getUserId());
        task.setStatus(taskDTO.getStatus());
        return task;
    }
}
