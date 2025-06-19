package org.lorem.consultationservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.consultationservice.domain.model.aggregates.Consultation;

import java.util.List;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    List<Consultation> findAllByLawyerId(Long lawyerId);
    List<Consultation> findAllByClientId(Long clientId);
    List<Consultation> findAllByClientIdAndLawyerId(Long clientId, Long lawyerId);
}