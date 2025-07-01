package org.lorem.communicationservice.interfaces.rest.resources;

public record CreateVideoCallResource(
        Long consultationId,
        String description,
        String location
) {
}
