package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.entities.MessageItem;
import org.lorem.communicationservice.interfaces.rest.resources.MessageResource;

public class MessageResourceFromEntityAssembler {
    public static MessageResource toResourceFromEntity(MessageItem entity){
        return new MessageResource(
                entity.getId(),
                entity.getContent(),
                entity.getChatRoom().getId(),
                entity.isRead(),
                entity.getSenderType().toString()
        );
    }
}
