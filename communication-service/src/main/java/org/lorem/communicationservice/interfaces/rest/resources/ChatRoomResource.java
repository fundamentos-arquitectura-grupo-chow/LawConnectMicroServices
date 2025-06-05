package org.lorem.communicationservice.interfaces.rest.resources;

import org.lorem.communicationservice.domain.model.entities.MessageItem;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;

import java.util.List;

public record ChatRoomResource(
        Long id,
        ConsultationResource consultation,
        String status,
        List<MessageResource> messages
) {
}
