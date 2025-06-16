package org.lorem.followupservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.followupservice.domain.model.aggregates.Notification;
import org.lorem.followupservice.domain.model.commands.CreateNotificationCommand;
import org.lorem.followupservice.domain.model.commands.DeleteNotificationCommand;
import org.lorem.followupservice.domain.services.NotificationCommandService;
import org.lorem.followupservice.infrastructure.persistence.jpa.repositories.NotificationRepository;

import java.util.Optional;

@Service
public class NotificationCommandServiceImpl implements NotificationCommandService {

    private final NotificationRepository notificationRepository;

    public NotificationCommandServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Optional<Notification> handle(CreateNotificationCommand command) {

        var notification = new Notification(command);

        notificationRepository.save(notification);

        return Optional.of(notification);
    }

    @Override
    public void handle(DeleteNotificationCommand command) {
        notificationRepository.deleteById(command.notificationId());
    }
}
