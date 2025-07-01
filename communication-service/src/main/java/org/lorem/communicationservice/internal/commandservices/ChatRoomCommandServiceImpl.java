package org.lorem.communicationservice.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.internal.outboundServices.ExternalConsultationCommunicationService;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.commands.CreateChatRoomCommand;
import org.lorem.communicationservice.domain.model.commands.DeleteChatRoomCommand;
import org.lorem.communicationservice.domain.services.ChatRoomCommandService;
import org.lorem.communicationservice.repositories.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomCommandServiceImpl implements ChatRoomCommandService {

    private final ChatRoomRepository chatRoomRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public ChatRoomCommandServiceImpl(ChatRoomRepository chatRoomRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.chatRoomRepository = chatRoomRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public Optional<ChatRoom> handle(CreateChatRoomCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        var chatRoom = new ChatRoom(consultation.get());

        chatRoomRepository.save(chatRoom);

        return Optional.of(chatRoom);
    }

    @Override
    public void handle(DeleteChatRoomCommand command) {
        var consultation = externalConsultationCommunicationService.getConsultationById(command.chatRoomId());
        var chatRoom = chatRoomRepository.findByConsultation(consultation.get());

        if (chatRoom.isEmpty()) {
            throw new IllegalArgumentException("Chat room not found");
        }

        chatRoomRepository.delete(chatRoom.get());
    }
}
