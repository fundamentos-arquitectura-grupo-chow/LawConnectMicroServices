package org.lorem.consultationservice.application.internal.eventHandlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.lorem.consultationservice.application.internal.outboundServices.ExternalCommunicationConsultationService;
import org.lorem.consultationservice.domain.model.events.CreateChatRoomEvent;

@Service
public class CreateChatRoomEventHandler {

    private final ExternalCommunicationConsultationService externalCommunicationConsultationService;

    public CreateChatRoomEventHandler(ExternalCommunicationConsultationService externalCommunicationConsultationService) {
        this.externalCommunicationConsultationService = externalCommunicationConsultationService;
    }

    @EventListener(CreateChatRoomEvent.class)
    public void onChatRoomCreated(CreateChatRoomEvent event) {
        System.out.println("hola");
        System.out.println("Chat room created for consultation: " + event.getConsultationId());
        externalCommunicationConsultationService.createChatRoom(
                event.getConsultationId()
            );
    }
}
