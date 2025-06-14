package org.lorem.legalcaseservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.queries.GetAllLegalCasesQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByIdQuery;
import org.lorem.legalcaseservice.domain.services.LegalCaseQueryService;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LegalCaseQueryServiceImpl implements LegalCaseQueryService {

    private final LegalCaseRepository legalCaseRepository;

    public LegalCaseQueryServiceImpl(LegalCaseRepository legalCaseRepository) {
        this.legalCaseRepository = legalCaseRepository;
    }

    @Override
    public List<LegalCase> handle(GetAllLegalCasesQuery query) {
        return legalCaseRepository.findAll();
    }

    @Override
    public Optional<LegalCase> handle(GetLegalCaseByIdQuery query) {
        return legalCaseRepository.findById(query.legalCaseId());
    }

    @Override
    public Optional<LegalCase> handle(GetLegalCaseByConsultationIdQuery query) {
        List<LegalCase> legalCases = legalCaseRepository.findByConsultationId(query.consultationId());

        if (legalCases.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(legalCases.get(0));
    }
}