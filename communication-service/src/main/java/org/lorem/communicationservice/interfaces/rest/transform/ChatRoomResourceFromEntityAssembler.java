package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.interfaces.rest.resources.ChatRoomResource;

public class ChatRoomResourceFromEntityAssembler {
    public static ChatRoomResource toResourceFromEntity(ChatRoom entity){
        return new ChatRoomResource(
                entity.getId(),
                entity.getConsultationId(),
                entity.getStatus().toString(),
                entity.getMessages().getMessages().stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity).toList()
        );
    }
}
