package org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories;

import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentsRepository extends JpaRepository<DocumentsItem, Long> {
    List<DocumentsItem> findAllByLegalCase(LegalCase legalCase);
}
