package org.lorem.followupservice.domain.services;

import org.lorem.followupservice.domain.model.queries.GetAllNotificationsQuery;
import org.springframework.stereotype.Service;
import org.lorem.followupservice.domain.model.aggregates.Notification;
import org.lorem.followupservice.domain.model.queries.GetAllNotificationByConsultationIdQuery;
import org.lorem.followupservice.domain.model.queries.GetAllNotificationsByClientIdQuery;
import org.lorem.followupservice.domain.model.queries.GetNotificationByIdQuery;

import java.util.List;
import java.util.Optional;

@Service
public interface NotificationQueryService {
    List<Notification> handle(GetAllNotificationsByClientIdQuery query);
    List<Notification> handle(GetAllNotificationByConsultationIdQuery query);
    Optional<Notification> handle(GetNotificationByIdQuery query);
    List<Notification> handle(GetAllNotificationsQuery query);
}
