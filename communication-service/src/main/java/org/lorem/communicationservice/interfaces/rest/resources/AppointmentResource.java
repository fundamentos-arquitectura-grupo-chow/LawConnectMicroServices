package org.lorem.communicationservice.interfaces.rest.resources;

public record AppointmentResource(
        Long id,
        String description,
        Long consultationId,
        String location,
        String status
) {
}
