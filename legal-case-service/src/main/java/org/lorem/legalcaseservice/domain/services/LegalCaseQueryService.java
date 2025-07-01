package org.lorem.legalcaseservice.domain.services;

import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.queries.GetAllLegalCasesQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByIdQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LegalCaseQueryService {
    List<LegalCase> handle(GetAllLegalCasesQuery query);
    Optional<LegalCase> handle(GetLegalCaseByIdQuery query);
    Optional<LegalCase> handle(GetLegalCaseByConsultationIdQuery query);
}
