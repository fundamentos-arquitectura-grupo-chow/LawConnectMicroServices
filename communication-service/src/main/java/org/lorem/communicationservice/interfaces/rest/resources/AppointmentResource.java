package org.lorem.communicationservice.interfaces.rest.resources;

public record AppointmentResource(
        Long id,
        String description,
        Long consultation,
        String location,
        String status
) {
}
