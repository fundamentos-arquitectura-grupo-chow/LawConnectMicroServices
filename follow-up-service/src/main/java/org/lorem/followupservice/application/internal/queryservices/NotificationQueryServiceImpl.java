package org.lorem.followupservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.followupservice.domain.model.aggregates.Notification;
import org.lorem.followupservice.domain.model.queries.GetAllNotificationByConsultationIdQuery;
import org.lorem.followupservice.domain.model.queries.GetAllNotificationsByClientIdQuery;
import org.lorem.followupservice.domain.model.queries.GetNotificationByIdQuery;
import org.lorem.followupservice.domain.services.NotificationQueryService;
import org.lorem.followupservice.infrastructure.persistence.jpa.repositories.NotificationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationQueryServiceImpl implements NotificationQueryService {

    private final NotificationRepository notificationRepository;

    public NotificationQueryServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<Notification> handle(GetAllNotificationsByClientIdQuery query) {
        return notificationRepository.findAllByClientId(query.clientId());
    }

    @Override
    public List<Notification> handle(GetAllNotificationByConsultationIdQuery query) {
        return notificationRepository.findAllByConsultationId(query.consultationId());
    }

    @Override
    public Optional<Notification> handle(GetNotificationByIdQuery query) {
        return notificationRepository.findById(query.Id());
    }
}
