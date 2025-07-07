package org.lorem.communicationservice.interfaces.rest.resources;

public record VideoCallResource(
        Long id,
        Long consultationId,
        String description,
        String status
) {
}
