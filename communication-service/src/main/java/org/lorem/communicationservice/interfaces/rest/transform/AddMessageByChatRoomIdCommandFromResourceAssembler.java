package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.commands.AddMessageByChatRoomIdCommand;
import org.lorem.communicationservice.interfaces.rest.resources.AddMessageByChatRoomIdResource;

public class AddMessageByChatRoomIdCommandFromResourceAssembler {
    public static AddMessageByChatRoomIdCommand toCommandFromResource(AddMessageByChatRoomIdResource resource){
        return new AddMessageByChatRoomIdCommand(
                resource.chatRoomId(),
                resource.message(),
                resource.sender()
        );
    }
}
