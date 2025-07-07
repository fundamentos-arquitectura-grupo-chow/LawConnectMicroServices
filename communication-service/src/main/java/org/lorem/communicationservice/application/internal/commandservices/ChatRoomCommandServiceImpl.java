package org.lorem.communicationservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.communicationservice.domain.model.commands.CreateChatRoomCommand;
import org.lorem.communicationservice.domain.model.commands.DeleteChatRoomCommand;
import org.lorem.communicationservice.domain.services.ChatRoomCommandService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.ChatRoomRepository;

import java.util.Optional;

@Service
public class ChatRoomCommandServiceImpl implements ChatRoomCommandService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomCommandServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public Optional<ChatRoom> handle(CreateChatRoomCommand command) {

        var chatRoom = new ChatRoom(command.consultationId());

        chatRoomRepository.save(chatRoom);

        return Optional.of(chatRoom);
    }

    @Override
    public void handle(DeleteChatRoomCommand command) {
        var chatRoom = chatRoomRepository.findById(command.chatRoomId());
        if (chatRoom.isEmpty()) {
            throw new IllegalArgumentException("Chat room not found");
        }
        chatRoomRepository.delete(chatRoom.get());
    }
}
