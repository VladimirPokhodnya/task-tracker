package com.github.vladimirpokhodnya.tasktracker.kafka;

import com.github.vladimirpokhodnya.tasktracker.model.TaskStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaTaskConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaTaskConsumer.class);


    @KafkaListener(
            id = "${tasktracker.kafka.consumer.group-id}",
            topics = "${tasktracker.kafka.topic.task_updates}", // Убедитесь, что это тот же топик, что и у продюсера
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listener(@Payload TaskStatus status,
                         @Header(KafkaHeaders.RECEIVED_KEY) Long id,
                         Acknowledgment ack) {
        log.debug("Received message with ID: {} and status: {}", id, status);

        log.debug("Task consumer: Обработка новых сообщений");
        try {
            // Выполняем бизнес-логику
            ack.acknowledge(); // Подтверждаем успешную обработку сообщения
            log.info("Successfully processed message with ID: {} and status: {}", id, status);
        } catch (Exception e) {
            log.error("Error processing message with ID: {}: {}", id, e.getMessage(), e);
            // Можно добавить дополнительную обработку ошибок
        }

        log.debug("Task consumer: записи обработаны");

    }
}
