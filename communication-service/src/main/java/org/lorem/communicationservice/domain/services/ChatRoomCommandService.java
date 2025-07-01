package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.commands.CreateChatRoomCommand;
import org.lorem.communicationservice.domain.model.commands.DeleteChatRoomCommand;

import java.util.Optional;

public interface ChatRoomCommandService {
    Optional<ChatRoom> handle(CreateChatRoomCommand command);
    void handle(DeleteChatRoomCommand command);
}
