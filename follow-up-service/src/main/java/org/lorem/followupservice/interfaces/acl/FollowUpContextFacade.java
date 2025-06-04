package org.lorem.followupservice.interfaces.acl;

import org.springframework.stereotype.Service;
import org.lorem.followupservice.domain.model.commands.CreateNotificationCommand;
import org.lorem.followupservice.domain.services.NotificationCommandService;
import org.lorem.followupservice.domain.services.NotificationQueryService;

@Service
public class FollowUpContextFacade {

    private final NotificationCommandService notificationCommandService;
    private final NotificationQueryService notificationQueryService;

    public FollowUpContextFacade(NotificationCommandService notificationCommandService, NotificationQueryService notificationQueryService) {
        this.notificationCommandService = notificationCommandService;
        this.notificationQueryService = notificationQueryService;
    }

    public void createNotification(
            String title,
            String description,
            Long clientId,
            Long consultationId
    ) {
        notificationCommandService.handle(new CreateNotificationCommand(
                        title,
                        description,
                        clientId,
                        consultationId
                )
        );
    }

}
