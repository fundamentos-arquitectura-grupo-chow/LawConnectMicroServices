package org.lorem.communicationservice.application.internal.queryservices;

import org.lorem.communicationservice.domain.model.entities.MessageItem;
import org.lorem.communicationservice.domain.model.queries.GetAllMessagesByChatRoomIdQuery;
import org.lorem.communicationservice.domain.services.MessageQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.ChatRoomRepository;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageQueryServiceImpl implements MessageQueryService {

    private final MessageRepository messageRepository;
    private final ChatRoomRepository chatRoomRepository;

    public MessageQueryServiceImpl(MessageRepository messageRepository, ChatRoomRepository chatRoomRepository) {
        this.messageRepository = messageRepository;
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public List<MessageItem> handle(GetAllMessagesByChatRoomIdQuery query) {
        var chatRoom = chatRoomRepository.findById(query.chatRoomId());
        return messageRepository.findAllByChatRoom(chatRoom.get());
    }
}
