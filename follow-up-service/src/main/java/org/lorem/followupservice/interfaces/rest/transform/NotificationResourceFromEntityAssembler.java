package org.lorem.followupservice.interfaces.rest.transform;

import org.lorem.followupservice.domain.model.aggregates.Notification;
import org.lorem.followupservice.interfaces.rest.resources.NotificationResource;

public class NotificationResourceFromEntityAssembler {
    public static NotificationResource toResourceFromEntity(Notification entity){
        return new NotificationResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getClientId(),
                entity.getConsultationId()
        );
    }
}
