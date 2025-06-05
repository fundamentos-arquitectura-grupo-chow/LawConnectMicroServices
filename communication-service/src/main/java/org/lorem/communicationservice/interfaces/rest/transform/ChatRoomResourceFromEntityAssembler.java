package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.interfaces.rest.resources.ChatRoomResource;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;

public class ChatRoomResourceFromEntityAssembler {
    public static ChatRoomResource toResourceFromEntity(ChatRoom entity, ConsultationResource consultationResource){
        return new ChatRoomResource(
                entity.getId(),
                consultationResource,
                entity.getStatus().toString(),
                entity.getMessages().getMessages().stream().map(MessageResourceFromEntityAssembler::toResourceFromEntity).toList()
        );
    }
}
