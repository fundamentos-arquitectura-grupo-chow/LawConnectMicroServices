package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.commands.AddMessageByChatRoomIdCommand;


public interface MessageCommandService {
    void handle(AddMessageByChatRoomIdCommand command);
}
