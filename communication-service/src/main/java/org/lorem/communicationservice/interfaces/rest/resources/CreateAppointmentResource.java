package org.lorem.communicationservice.interfaces.rest.resources;

public record CreateAppointmentResource (
        Long consultationId,
        String description,
        String location
) {
}
