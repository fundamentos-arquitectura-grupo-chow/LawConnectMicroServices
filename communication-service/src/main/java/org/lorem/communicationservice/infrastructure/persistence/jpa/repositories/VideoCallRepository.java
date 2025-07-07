package org.lorem.communicationservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.communicationservice.domain.model.aggregates.VideoCall;

import java.util.List;

@Repository
public interface VideoCallRepository extends JpaRepository<VideoCall, Long> {
    List<VideoCall> findAllByConsultationId(Long consultationId);
}
