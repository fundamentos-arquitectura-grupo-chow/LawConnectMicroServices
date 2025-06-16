package org.lorem.followupservice.interfaces.rest.resources;

public record CreateNotificationResource(
        String title,
        String description,
        Long clientId,
        Long consultationId
) {
}
