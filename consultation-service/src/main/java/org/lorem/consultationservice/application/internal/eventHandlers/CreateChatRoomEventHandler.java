package org.lorem.consultationservice.application.internal.eventHandlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.lorem.consultationservice.domain.model.events.CreateChatRoomEvent;
import org.lorem.consultationservice.infrastructure.kafka.CommunicationKafkaProducer;

@Service
public class CreateChatRoomEventHandler {

    private final CommunicationKafkaProducer communicationKafkaProducer;

    public CreateChatRoomEventHandler(CommunicationKafkaProducer communicationKafkaProducer) {
        this.communicationKafkaProducer = communicationKafkaProducer;
    }

    @EventListener(CreateChatRoomEvent.class)
    public void onChatRoomCreated(CreateChatRoomEvent event) {
        System.out.println("Chat room created for consultation: " + event.getConsultationId());
        communicationKafkaProducer.createChatRoom(event.getConsultationId());
    }
}