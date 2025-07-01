package org.lorem.communicationservice.interfaces.rest.resources;

public record VideoCallResource(
        Long id,
        Long consultation,
        String description,
        String status
) {
}
