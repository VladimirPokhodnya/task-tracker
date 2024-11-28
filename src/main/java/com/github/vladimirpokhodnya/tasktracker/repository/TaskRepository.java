package com.github.vladimirpokhodnya.tasktracker.repository;


import com.github.vladimirpokhodnya.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
