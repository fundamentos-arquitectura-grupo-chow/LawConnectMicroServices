package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.interfaces.rest.resources.AppointmentResource;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;

public class AppointmentResourceFromEntityAssembler {
    public static AppointmentResource toResourceFromEntity(Appointment entity, ConsultationResource consultationResource) {
        return new AppointmentResource(
                entity.getId(),
                entity.getDescription(),
                consultationResource,
                entity.getLocation(),
                entity.getStatus().toString()
        );
    }
}
