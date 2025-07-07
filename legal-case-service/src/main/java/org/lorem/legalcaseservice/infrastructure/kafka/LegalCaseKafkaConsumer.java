package org.lorem.legalcaseservice.infrastructure.kafka;

import org.lorem.legalcaseservice.interfaces.acl.LegalCaseContextFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LegalCaseKafkaConsumer {

    private final LegalCaseContextFacade legalCaseContextFacade;

    public LegalCaseKafkaConsumer(LegalCaseContextFacade legalCaseContextFacade) {
        this.legalCaseContextFacade = legalCaseContextFacade;
    }

    @KafkaListener(topics = "legalcase-topic", groupId = "legalcase-group")
    public void consumeMessage(String message) {
        if (message.startsWith("LEGALCASE_CREATED:")) {
            String[] parts = message.split(":");
            String title = parts[1];
            String description = parts[2];
            Long consultationId = Long.parseLong(parts[3]);
            handleLegalCaseCreated(title, description, consultationId);
        } else if (message.startsWith("LEGALCASE_DELETED:")) {
            Long legalCaseId = Long.parseLong(message.split(":")[1]);
            handleLegalCaseDeleted(legalCaseId);
        }
    }

    private void handleLegalCaseCreated(String title, String description, Long consultationId) {
        System.out.println("Handling legal case creation for ID: " + consultationId);
        legalCaseContextFacade.createLegalCase(title, description, consultationId);
    }

    private void handleLegalCaseDeleted(Long legalCaseId) {
        System.out.println("Handling legal case deletion for ID: " + legalCaseId);
        legalCaseContextFacade.deleteLegalCase(legalCaseId);
    }
}