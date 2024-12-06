package com.github.vladimirpokhodnya.tasktracker.kafka;

import com.github.vladimirpokhodnya.tasktracker.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaTaskProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaTaskProducer.class);

    private final KafkaTemplate<TaskStatus, Long> template;

    public KafkaTaskProducer(KafkaTemplate<TaskStatus, Long> template) {
        this.template = template;
    }

    public void send(Long id, TaskStatus status) {
        try {
            template.sendDefault(status, id).get();
            template.flush();
            log.info("Sent message: {} with ID: {}", status, id);
        } catch (Exception ex) {
            log.error("Error sending message: {}", ex.getMessage(), ex);
        }
    }

}