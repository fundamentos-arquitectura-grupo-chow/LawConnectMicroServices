package org.lorem.communicationservice.infrastructure.kafka;

import org.lorem.communicationservice.interfaces.acl.CommunicationContextFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CommunicationKafkaConsumer {

    private final CommunicationContextFacade communicationContextFacade;

    public CommunicationKafkaConsumer(CommunicationContextFacade communicationContextFacade) {
        this.communicationContextFacade = communicationContextFacade;
    }

    @KafkaListener(topics = "communication-topic", groupId = "communication-group")
    public void consumeMessage(String message) {
        if (message.startsWith("CREATE_CHAT_ROOM:")) {
            Long consultationId = Long.parseLong(message.split(":")[1]);
            createChatRoom(consultationId);
        } else if (message.startsWith("DELETE_CHAT_ROOM:")) {
            Long consultationId = Long.parseLong(message.split(":")[1]);
            deleteChatRoom(consultationId);
        }
    }

    private void createChatRoom(Long consultationId) {
        System.out.println("Creating chat room for consultation ID: " + consultationId);
        communicationContextFacade.createChatRoom(consultationId);
    }

    private void deleteChatRoom(Long consultationId) {
        System.out.println("Deleting chat room for consultation ID: " + consultationId);
        communicationContextFacade.deleteChatRoom(consultationId);
    }
}