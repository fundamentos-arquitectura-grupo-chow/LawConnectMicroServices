package org.lorem.communicationservice.interfaces.rest.resources;

import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;

public record AppointmentResource(
        Long id,
        String description,
        ConsultationResource consultation,
        String location,
        String status
) {
}
