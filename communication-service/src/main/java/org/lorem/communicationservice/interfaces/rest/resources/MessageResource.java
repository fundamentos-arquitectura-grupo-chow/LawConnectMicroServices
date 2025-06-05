package org.lorem.communicationservice.interfaces.rest.resources;

import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;

public record MessageResource (
        Long id,
        String content,
        Long chatRoomId,
        boolean isRead,
        String senderType
) {
}
