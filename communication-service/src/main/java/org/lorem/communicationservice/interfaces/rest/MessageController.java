package org.lorem.communicationservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.lorem.communicationservice.domain.model.queries.GetAllMessagesByChatRoomIdQuery;
import org.lorem.communicationservice.domain.services.MessageCommandService;
import org.lorem.communicationservice.domain.services.MessageQueryService;
import org.lorem.communicationservice.interfaces.rest.resources.AddMessageByChatRoomIdResource;
import org.lorem.communicationservice.interfaces.rest.resources.MessageResource;
import org.lorem.communicationservice.interfaces.rest.transform.AddMessageByChatRoomIdCommandFromResourceAssembler;
import org.lorem.communicationservice.interfaces.rest.transform.MessageResourceFromEntityAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/message", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Message", description = "Message Endpoints")
public class MessageController {

    private final MessageCommandService messageCommandService;
    private final MessageQueryService messageQueryService;

    public MessageController(MessageCommandService messageCommandService, MessageQueryService messageQueryService) {
        this.messageCommandService = messageCommandService;
        this.messageQueryService = messageQueryService;
    }

    @PostMapping
    public ResponseEntity<?> addMessageByChatRoomId(@RequestBody AddMessageByChatRoomIdResource resource) {
        var addMessageByChatRoomIdCommand = AddMessageByChatRoomIdCommandFromResourceAssembler.toCommandFromResource(resource);
        messageCommandService.handle(addMessageByChatRoomIdCommand);
        return ResponseEntity.ok("Message added successfully");
    }

    @GetMapping("/{chatRoomId}")
    public ResponseEntity<List<MessageResource>> getAllMessageByChatRoomId(@PathVariable Long chatRoomId) {
        var message = messageQueryService.handle(new GetAllMessagesByChatRoomIdQuery(chatRoomId));
        if (message.isEmpty()) return ResponseEntity.notFound().build();
        var messageResource = message.stream()
                .map(MessageResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(messageResource);
    }
}
