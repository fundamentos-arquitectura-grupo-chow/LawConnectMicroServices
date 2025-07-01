package org.lorem.communicationservice.interfaces.acl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.commands.CreateChatRoomCommand;
import org.lorem.communicationservice.domain.model.commands.DeleteChatRoomCommand;
import org.lorem.communicationservice.domain.services.ChatRoomCommandService;

@Service
public class CommunicationContextFacade {

    private final ChatRoomCommandService chatRoomCommandService;

    public CommunicationContextFacade(
            @Lazy ChatRoomCommandService chatRoomCommandService
    ) {
        this.chatRoomCommandService = chatRoomCommandService;
    }

    public void createChatRoom(
            Long consultationId
    ){
        chatRoomCommandService.handle(new CreateChatRoomCommand(
                        consultationId
                )
        );
    }

    public void deleteChatRoom(
            Long consultationId
    ){
        chatRoomCommandService.handle(new DeleteChatRoomCommand(
                        consultationId
                )
        );
    }
}
