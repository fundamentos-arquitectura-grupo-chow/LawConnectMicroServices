package org.lorem.communicationservice.domain.model.commands;

public record CreateVideoCallCommand(
        Long consultationId,
        String description
) {
}
