package org.lorem.communicationservice.interfaces.rest.resources;

import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;

public record VideoCallResource(
        Long id,
        ConsultationResource consultation,
        String description,
        String status
) {
}
