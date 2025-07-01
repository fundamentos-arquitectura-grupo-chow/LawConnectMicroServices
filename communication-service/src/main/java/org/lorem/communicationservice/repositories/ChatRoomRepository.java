package org.lorem.communicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.communicationservice.domain.model.aggregates.ChatRoom;
import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByConsultation(Consultation consultation);
}
