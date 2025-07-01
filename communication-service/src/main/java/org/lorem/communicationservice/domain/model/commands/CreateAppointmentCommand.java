package org.lorem.communicationservice.domain.model.commands;

public record CreateAppointmentCommand(
        Long consultationId,
        String description,
        String location
) {
}
