package org.lorem.communicationservice.interfaces.rest.resources;

public record MessageResource (
        Long id,
        String content,
        Long chatRoomId,
        boolean isRead,
        String senderType
) {
}
