package org.lorem.followupservice.infrastructure.kafka;

import org.lorem.followupservice.interfaces.acl.FollowUpContextFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class FollowUpKafkaConsumer {

    private final FollowUpContextFacade followUpContextFacade;

    public FollowUpKafkaConsumer(FollowUpContextFacade followUpContextFacade) {
        this.followUpContextFacade = followUpContextFacade;
    }

    @KafkaListener(topics = "followup-topic", groupId = "followup-group")
    public void consumeMessage(String message) {
        if (message.startsWith("CREATE_NOTIFICATION:")) {
            String[] parts = message.split(":");
            String title = parts[1];
            String description = parts[2];
            Long clientId = Long.parseLong(parts[3]);
            Long consultationId = Long.parseLong(parts[4]);
            createNotification(title, description, clientId, consultationId);
        }
    }

    private void createNotification(String title, String description, Long clientId, Long consultationId) {
        System.out.println("Creating notification for consultation ID: " + consultationId);
        followUpContextFacade.createNotification(title, description, clientId, consultationId);
    }
}