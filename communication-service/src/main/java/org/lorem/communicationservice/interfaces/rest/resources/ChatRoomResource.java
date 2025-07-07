package org.lorem.communicationservice.interfaces.rest.resources;


import java.util.List;

public record ChatRoomResource(
        Long id,
        Long consultationId,
        String status,
        List<MessageResource> messages
) {
}
