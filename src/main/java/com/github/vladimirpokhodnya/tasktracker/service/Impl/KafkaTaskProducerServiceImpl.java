package com.github.vladimirpokhodnya.tasktracker.service.Impl;

import com.github.vladimirpokhodnya.tasktracker.model.dto.TaskDTO;
import com.github.vladimirpokhodnya.tasktracker.service.KafkaTaskProducerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaTaskProducerServiceImpl implements KafkaTaskProducerService {

    @Value("${task.kafka.consumer.group-id}")
    private String groupId;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaTaskProducerServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sent(TaskDTO taskDTO) {
            String message = taskDTO.getId() + ":" + taskDTO.getStatus();
            kafkaTemplate.send(groupId, message);
    }
}
