package org.lorem.communicationservice.interfaces.rest.resources;


import java.util.List;

public record ChatRoomResource(
        Long id,
        Long consultation,
        String status,
        List<MessageResource> messages
) {
}
