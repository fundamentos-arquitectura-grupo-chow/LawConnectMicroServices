package org.lorem.consultationservice.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ConsultationKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ConsultationKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        kafkaTemplate.send("consultation-topic", message);
    }
}