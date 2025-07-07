package org.lorem.consultationservice.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CommunicationKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public CommunicationKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void createChatRoom(Long consultationId) {
        String message = "CREATE_CHAT_ROOM:" + consultationId;
        kafkaTemplate.send("communication-topic", message);
    }

    public void deleteChatRoom(Long consultationId) {
        String message = "DELETE_CHAT_ROOM:" + consultationId;
        kafkaTemplate.send("communication-topic", message);
    }
}