package org.lorem.communicationservice.infrastructure.persistence.jpa.repositories;

import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByConsultation(Consultation consultation);
}
