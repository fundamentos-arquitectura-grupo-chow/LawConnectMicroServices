package org.lorem.consultationservice.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FollowUpKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public FollowUpKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createNotification(String title, String description, Long clientId, Long consultationId) {
        String message = String.format("CREATE_NOTIFICATION:%s:%s:%d:%d", title, description, clientId, consultationId);
        kafkaTemplate.send("followup-topic", message);
    }
}