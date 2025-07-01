package org.lorem.legalcaseservice.interfaces.rest.transform;

import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.interfaces.rest.resources.LegalCaseResource;

public class LegalCaseResourceFromEntityAssembler {
    public static LegalCaseResource toEntityFromResource(LegalCase entity, ConsultationResource consultationResource) {
        return new LegalCaseResource(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),
                entity.getStatus().name(),
                consultationResource,
                entity.getDocuments().getDocumentsItems().stream().map(DocumentsItemResourceFromEntityAssembler::toEntityFromResource).toList()
        );
    }
}
