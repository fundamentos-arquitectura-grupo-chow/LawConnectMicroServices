package org.lorem.legalcaseservice.application.internal.queryservices;

import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.queries.GetAllLegalCasesQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByIdQuery;
import org.lorem.legalcaseservice.domain.services.LegalCaseQueryService;
import org.lorem.legalcaseservice.infrastructure.grpc.ConsultationGrpcClient;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LegalCaseQueryServiceImpl implements LegalCaseQueryService {

    private final LegalCaseRepository legalCaseRepository;
    private final ConsultationGrpcClient consultationGrpcClient;

    public LegalCaseQueryServiceImpl(LegalCaseRepository legalCaseRepository, ConsultationGrpcClient consultationGrpcClient) {
        this.legalCaseRepository = legalCaseRepository;
        this.consultationGrpcClient = consultationGrpcClient;
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
        var consultationId = query.consultationId();
        var clientId = consultationGrpcClient.getClientIdByConsultationId(consultationId);
        return legalCaseRepository.findByConsultation(clientId);
    }
}
