package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.entities.MessageItem;
import org.lorem.communicationservice.domain.model.queries.GetAllMessagesByChatRoomIdQuery;

import java.util.List;
import java.util.Optional;

public interface MessageQueryService {
    List<MessageItem> handle(GetAllMessagesByChatRoomIdQuery query);
}
