package com.github.vladimirpokhodnya.tasktracker.service;

import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskDTO;
import org.springframework.stereotype.Service;

@Service
public interface KafkaTaskProducerService {
     void sent(TaskDTO taskDTO);
 }
