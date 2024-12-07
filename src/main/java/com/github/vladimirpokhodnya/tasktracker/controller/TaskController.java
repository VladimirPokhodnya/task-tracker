package com.github.vladimirpokhodnya.tasktracker.controller;

import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.HandlingResult;
import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogExecution;
import com.github.vladimirpokhodnya.tasktracker.aspect.annotation.LogTracking;
import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskDTO;
import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskStatusDTO;
import com.github.vladimirpokhodnya.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Value("${task.kafka.consumer.group-id}")
    private String groupId;

    private final TaskService taskService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public TaskController(TaskService taskService, KafkaTemplate<String, String> kafkaTemplate) {
        this.taskService = taskService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @LogExecution
    @HandlingResult
    @LogTracking
    @PostMapping
    public TaskDTO createTask(@RequestBody TaskDTO taskDTO) {
        return taskService.createTask(taskDTO);
    }

    @LogExecution
    @HandlingResult
    @LogTracking
    @GetMapping("/{id}")
    public Optional<TaskDTO> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @LogExecution
    @HandlingResult
    @LogTracking
    @PutMapping("/{id}")
    public Optional<TaskDTO> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return taskService.updateTask(id, taskDTO);
    }

    @LogExecution
    @HandlingResult
    @LogTracking
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @LogExecution
    @HandlingResult
    @LogTracking
    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }


    @PatchMapping("/{id}/status")
    public Optional<TaskDTO> updateTaskStatus(@PathVariable Long id, @RequestBody TaskStatusDTO statusDTO) {
        Optional<TaskDTO> updatedTask = taskService.updateStatus(id, statusDTO.status());
        updatedTask.ifPresent(task -> {
            String message = id + ":" + statusDTO.status().name();
            kafkaTemplate.send(groupId, message);
        });
        return updatedTask;
    }
}
