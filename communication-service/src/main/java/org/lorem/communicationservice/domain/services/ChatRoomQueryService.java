package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.queries.GetChatRoomByConsultationIdQuery;

import java.util.Optional;

public interface ChatRoomQueryService {
    Optional<ChatRoom> handle(GetChatRoomByConsultationIdQuery query);
}
