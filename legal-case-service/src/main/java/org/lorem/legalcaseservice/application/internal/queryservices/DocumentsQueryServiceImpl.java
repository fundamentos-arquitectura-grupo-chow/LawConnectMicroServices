package org.lorem.legalcaseservice.application.internal.queryservices;

import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import org.lorem.legalcaseservice.domain.model.queries.GetAllDocumentsByLegalCaseQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetDocumentByIdQuery;
import org.lorem.legalcaseservice.domain.services.DocumentsQueryService;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.DocumentsRepository;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentsQueryServiceImpl implements DocumentsQueryService {

    private final DocumentsRepository documentsRepository;
    private final LegalCaseRepository legalCaseRepository;

    public DocumentsQueryServiceImpl(DocumentsRepository documentsRepository, LegalCaseRepository legalCaseRepository) {
        this.documentsRepository = documentsRepository;
        this.legalCaseRepository = legalCaseRepository;
    }

    @Override
    public List<DocumentsItem> handle(GetAllDocumentsByLegalCaseQuery query) {
        var legalCase = legalCaseRepository.findById(query.legalCaseId());
        return documentsRepository.findAllByLegalCase(legalCase.get());
    }

    @Override
    public Optional<DocumentsItem> handle(GetDocumentByIdQuery query) {
        return documentsRepository.findById(query.documentId());
    }
}
