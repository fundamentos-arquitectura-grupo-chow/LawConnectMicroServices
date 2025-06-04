package org.lorem.followupservice.interfaces.rest.transform;

import org.lorem.followupservice.domain.model.commands.CreateNotificationCommand;
import org.lorem.followupservice.interfaces.rest.resources.CreateNotificationResource;

public class CreateNotificationCommandFromResourceAssembler {
    public static CreateNotificationCommand toCommandFromResource(CreateNotificationResource resource) {
        return new CreateNotificationCommand(
                resource.title(),
                resource.description(),
                resource.clientId(),
                resource.consultationId()
        );
    }
}
