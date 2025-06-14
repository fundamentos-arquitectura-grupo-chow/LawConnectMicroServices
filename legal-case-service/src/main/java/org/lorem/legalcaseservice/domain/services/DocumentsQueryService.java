package org.lorem.legalcaseservice.domain.services;

import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import org.lorem.legalcaseservice.domain.model.queries.GetAllDocumentsByLegalCaseQuery;
import org.lorem.legalcaseservice.domain.model.queries.GetDocumentByIdQuery;

import java.util.List;
import java.util.Optional;

@Service
public interface DocumentsQueryService {
    List<DocumentsItem> handle(GetAllDocumentsByLegalCaseQuery query);
    Optional<DocumentsItem> handle(GetDocumentByIdQuery query);
}
