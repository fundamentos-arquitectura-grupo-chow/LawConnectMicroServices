package org.lorem.consultationservice.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LegalCaseKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public LegalCaseKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendLegalCaseCreatedMessage(String title, String description, Long consultationId) {
        String message = String.format("LEGALCASE_CREATED:%s:%s:%d",
            title,
            description,
            consultationId
        );
        kafkaTemplate.send("legalcase-topic", message);
    }

    public void sendLegalCaseDeletedMessage(Long legalCaseId) {
        String message = String.format("LEGALCASE_DELETED:%d", legalCaseId);
        kafkaTemplate.send("legalcase-topic", message);
    }
}