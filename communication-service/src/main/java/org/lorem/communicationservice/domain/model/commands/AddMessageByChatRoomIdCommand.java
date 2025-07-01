package org.lorem.communicationservice.domain.model.commands;

public record AddMessageByChatRoomIdCommand(
        Long chatRoomId,
        String message,
        Integer sender
) {
}
