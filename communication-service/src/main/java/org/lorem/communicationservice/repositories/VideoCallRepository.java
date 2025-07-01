package org.lorem.communicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.communicationservice.domain.model.aggregates.VideoCall;
import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;

import java.util.List;

@Repository
public interface VideoCallRepository extends JpaRepository<VideoCall, Long> {
    List<VideoCall> findAllByConsultation(Consultation consultation);
}
