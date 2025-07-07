package org.lorem.communicationservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.lorem.communicationservice.domain.model.commands.CreateChatRoomCommand;
import org.lorem.communicationservice.domain.model.queries.GetChatRoomByConsultationIdQuery;
import org.lorem.communicationservice.domain.services.ChatRoomCommandService;
import org.lorem.communicationservice.domain.services.ChatRoomQueryService;
import org.lorem.communicationservice.interfaces.rest.resources.ChatRoomResource;
import org.lorem.communicationservice.interfaces.rest.transform.ChatRoomResourceFromEntityAssembler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/chatRoom", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ChatRoom", description = "ChatRoom Endpoints")
public class ChatRoomController {

    private final ChatRoomCommandService chatRoomCommandService;
    private final ChatRoomQueryService chatRoomQueryService;

    public ChatRoomController(ChatRoomCommandService chatRoomCommandService, ChatRoomQueryService chatRoomQueryService) {
        this.chatRoomCommandService = chatRoomCommandService;
        this.chatRoomQueryService = chatRoomQueryService;
    }


    @PostMapping("/{consultationId}")
    public ResponseEntity<ChatRoomResource> createChatRoom(@PathVariable Long consultationId) {
        var createChatRoomCommand = new CreateChatRoomCommand(consultationId);
        var chatRoom = chatRoomCommandService.handle(createChatRoomCommand);
        if (chatRoom.isEmpty()) return ResponseEntity.badRequest().build();
        var chatRoomResource = ChatRoomResourceFromEntityAssembler.toResourceFromEntity(chatRoom.get());
        return new ResponseEntity<>(chatRoomResource, HttpStatus.CREATED);
    }

    @GetMapping("/{consultationId}")
    public ResponseEntity<ChatRoomResource> getChatRoomByConsultationId(@PathVariable Long consultationId) {
        var chatRoom = chatRoomQueryService.handle(new GetChatRoomByConsultationIdQuery(consultationId));
        if (chatRoom.isEmpty()) return ResponseEntity.notFound().build();
        var chatRoomResource = ChatRoomResourceFromEntityAssembler.toResourceFromEntity(chatRoom.get());
        return ResponseEntity.ok(chatRoomResource);
    }
}
