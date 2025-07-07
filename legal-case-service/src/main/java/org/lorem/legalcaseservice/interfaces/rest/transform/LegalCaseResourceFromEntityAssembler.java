package org.lorem.legalcaseservice.interfaces.rest.transform;

import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.interfaces.rest.resources.LegalCaseResource;

public class LegalCaseResourceFromEntityAssembler {
    public static LegalCaseResource toEntityFromResource(LegalCase entity) {
        return new LegalCaseResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus().name(),
                entity.getConsultationId(),
                entity.getDocuments().getDocumentsItems().stream().map(DocumentsItemResourceFromEntityAssembler::toEntityFromResource).toList()
        );
    }
}
