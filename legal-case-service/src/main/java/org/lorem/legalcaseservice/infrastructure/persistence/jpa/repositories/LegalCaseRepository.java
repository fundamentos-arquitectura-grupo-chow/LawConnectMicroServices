package org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories;

import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LegalCaseRepository extends JpaRepository<LegalCase, Long> {
    Optional<LegalCase> findByConsultation(Long consultation);
}
