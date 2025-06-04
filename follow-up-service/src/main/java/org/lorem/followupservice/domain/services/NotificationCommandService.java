package org.lorem.followupservice.domain.services;

import org.springframework.stereotype.Service;
import org.lorem.followupservice.domain.model.aggregates.Notification;
import org.lorem.followupservice.domain.model.commands.CreateNotificationCommand;
import org.lorem.followupservice.domain.model.commands.DeleteNotificationCommand;

import java.util.Optional;

@Service
public interface NotificationCommandService {
    Optional<Notification> handle(CreateNotificationCommand command);
    void handle(DeleteNotificationCommand command);
}
