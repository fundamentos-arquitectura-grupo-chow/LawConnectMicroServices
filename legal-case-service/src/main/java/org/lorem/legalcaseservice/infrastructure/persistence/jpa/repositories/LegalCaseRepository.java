package org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;

import java.util.List;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    List<LegalCase> findByConsultationId(Long consultationId);
}
