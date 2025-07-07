package org.lorem.communicationservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.commands.AddMessageByChatRoomIdCommand;
import org.lorem.communicationservice.domain.model.entities.MessageItem;
import org.lorem.communicationservice.domain.services.MessageCommandService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.ChatRoomRepository;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.MessageRepository;

@Service
public class MessageCommandServiceImpl implements MessageCommandService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;

    public MessageCommandServiceImpl(ChatRoomRepository chatRoomRepository, MessageRepository messageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public void handle(AddMessageByChatRoomIdCommand command) {

        var chatRoom = chatRoomRepository.findById(command.chatRoomId());

        if (chatRoom.isEmpty()) {
            throw new IllegalArgumentException("Chat room not found");
        }

        var message = new MessageItem(command, chatRoom.get());

        chatRoom.get().getMessages().addMessage(message);

        messageRepository.save(message);

    }
}
