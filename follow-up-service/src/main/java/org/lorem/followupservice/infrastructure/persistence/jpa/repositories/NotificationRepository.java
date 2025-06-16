package org.lorem.followupservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.followupservice.domain.model.aggregates.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findAllByConsultationId(Long consultationId);
    List<Notification> findAllByClientId(Long clientId);
}
