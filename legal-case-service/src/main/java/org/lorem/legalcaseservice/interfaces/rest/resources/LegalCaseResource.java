package org.lorem.legalcaseservice.interfaces.rest.resources;

import com.loremipsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

import java.util.List;

public record LegalCaseResource(
        Long id,
        String title,
        String description,
        String status,
        ConsultationResource consultationId,
        List<DocumentsItemResource> documents
) {
}
