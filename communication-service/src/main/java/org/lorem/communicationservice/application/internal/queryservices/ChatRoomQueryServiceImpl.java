package org.lorem.communicationservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.queries.GetChatRoomByConsultationIdQuery;
import org.lorem.communicationservice.domain.services.ChatRoomQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomQueryServiceImpl implements ChatRoomQueryService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomQueryServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public Optional<ChatRoom> handle(GetChatRoomByConsultationIdQuery query) {
        return chatRoomRepository.findByConsultationId(query.consultationId());
    }
}
