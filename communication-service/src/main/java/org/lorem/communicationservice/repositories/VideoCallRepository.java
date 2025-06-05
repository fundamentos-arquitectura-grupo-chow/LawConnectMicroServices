package org.lorem.communicationservice.infrastructure.persistence.jpa.repositories;

import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoCallRepository extends JpaRepository<VideoCall, Long> {
    List<VideoCall> findAllByConsultation(Consultation consultation);
}
