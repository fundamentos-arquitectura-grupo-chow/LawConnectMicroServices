package org.lorem.communicationservice.interfaces.rest.resources;

public record AddMessageByChatRoomIdResource(
        Long chatRoomId,
        String message,
        Integer sender
) {
}
