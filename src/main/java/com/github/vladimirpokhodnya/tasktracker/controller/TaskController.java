package com.github.vladimirpokhodnya.tasktracker.controller;

import com.github.vladimirpokhodnya.aophttploggingstarter.aspect.old.annotation.HandlingResult;
import com.github.vladimirpokhodnya.aophttploggingstarter.aspect.old.annotation.LogExecution;
import com.github.vladimirpokhodnya.aophttploggingstarter.aspect.old.annotation.LogTracking;
import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskDTO;
import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskStatusDTO;
import com.github.vladimirpokhodnya.tasktracker.service.TaskService;
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

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
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

    @LogExecution
    @HandlingResult
    @LogTracking
    @PatchMapping
    public Optional<TaskDTO> updateTaskStatus(@RequestBody TaskStatusDTO statusDTO) {
        return taskService.updateStatus(statusDTO.id(), statusDTO.status());
    }
}
