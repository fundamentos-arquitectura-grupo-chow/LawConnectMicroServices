package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.interfaces.rest.resources.AppointmentResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity) {
        return new AppointmentResource(
                entity.getId(),
                entity.getDescription(),
                entity.getConsultationId(),
                entity.getLocation(),
                entity.getStatus().toString()
        );
    }
}
