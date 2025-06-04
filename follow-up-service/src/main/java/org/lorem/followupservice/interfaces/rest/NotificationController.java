package org.lorem.followupservice.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.lorem.followupservice.domain.model.commands.DeleteNotificationCommand;
import org.lorem.followupservice.domain.model.queries.GetAllNotificationByConsultationIdQuery;
import org.lorem.followupservice.domain.model.queries.GetAllNotificationsByClientIdQuery;
import org.lorem.followupservice.domain.model.queries.GetNotificationByIdQuery;
import org.lorem.followupservice.domain.services.NotificationCommandService;
import org.lorem.followupservice.domain.services.NotificationQueryService;
import org.lorem.followupservice.interfaces.rest.resources.CreateNotificationResource;
import org.lorem.followupservice.interfaces.rest.resources.NotificationResource;
import org.lorem.followupservice.interfaces.rest.transform.CreateNotificationCommandFromResourceAssembler;
import org.lorem.followupservice.interfaces.rest.transform.NotificationResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/notification", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Notifications", description = "Notifications Endpoints")
public class NotificationController {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public NotificationController(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    @PostMapping
    public ResponseEntity<NotificationResource> createNotification(@RequestBody CreateNotificationResource createNotificationResource) {
        var createNotificationCommand = CreateNotificationCommandFromResourceAssembler.toCommandFromResource(createNotificationResource);
        var notification = notificationCommandService.handle(createNotificationCommand);
        if(notification.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return new ResponseEntity<>(notificationResource, HttpStatus.CREATED);
    }

    @GetMapping("/{notificationId}")
    public ResponseEntity<NotificationResource> getNotificationById(@PathVariable Long notificationId) {
        var getNotificationByIdQuery = new GetNotificationByIdQuery(notificationId);
        var notification = notificationQueryService.handle(getNotificationByIdQuery);
        if(notification.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var notificationResource = NotificationResourceFromEntityAssembler.toResourceFromEntity(notification.get());
        return ResponseEntity.ok(notificationResource);
    }

    @GetMapping("/legal-case/{consultationId}")
    public ResponseEntity<List<NotificationResource>> getAllNotificationsByConsultationId(@PathVariable Long consultationId) {
        var notifications = notificationQueryService.handle(new GetAllNotificationByConsultationIdQuery(consultationId));
        var notificationsResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(notificationsResources);
    }


    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<NotificationResource>> getAllNotificationsByClientId(@PathVariable Long clientId) {
        var notifications = notificationQueryService.handle(new GetAllNotificationsByClientIdQuery(clientId));
        var notificationsResources = notifications.stream()
                .map(NotificationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(notificationsResources);
    }

    @DeleteMapping("/{notificationId}")
    public ResponseEntity<?> deleteNotification(@PathVariable Long notificationId) {
        notificationCommandService.handle(new DeleteNotificationCommand(notificationId));
        return ResponseEntity.ok("Notification deleted successfully");
    }

}
