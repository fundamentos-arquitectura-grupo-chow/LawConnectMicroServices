package org.lorem.communicationservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.application.internal.outboundServices.ExternalConsultationCommunicationService;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.queries.GetChatRoomByConsultationIdQuery;
import org.lorem.communicationservice.domain.services.ChatRoomQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomQueryServiceImpl implements ChatRoomQueryService {

    private final ChatRoomRepository chatRoomRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public ChatRoomQueryServiceImpl(ChatRoomRepository chatRoomRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.chatRoomRepository = chatRoomRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public Optional<ChatRoom> handle(GetChatRoomByConsultationIdQuery query) {
        var consultation = externalConsultationCommunicationService.getConsultationById(query.consultationId());
        return chatRoomRepository.findByConsultation(consultation.get());
    }
}
