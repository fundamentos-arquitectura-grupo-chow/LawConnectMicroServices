package org.lorem.followupservice.interfaces.rest.resources;

public record NotificationResource(
        Long id,
        String title,
        String description,
        Long clientId,
        Long consultationId
) {
}
