package org.lorem.legalcaseservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import org.lorem.legalcaseservice.application.internal.outboundServices.ExternalFollowUpLegalCaseService;
import org.lorem.legalcaseservice.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import org.lorem.legalcaseservice.domain.model.commands.ChangeDocumentStatusCommand;
import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import org.lorem.legalcaseservice.domain.services.DocumentsCommandService;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.DocumentsRepository;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;

@Service
public class DocumentsCommandServiceImpl implements DocumentsCommandService {

    private final DocumentsRepository documentsRepository;
    private final LegalCaseRepository legalCaseRepository;
    private final ExternalFollowUpLegalCaseService externalFollowUpLegalCaseService;
    private final ExternalConsultationLegalCaseService externalConsultationLegalCaseService;

    public DocumentsCommandServiceImpl(DocumentsRepository documentsRepository, LegalCaseRepository legalCaseRepository, ExternalFollowUpLegalCaseService externalFollowUpLegalCaseService, ExternalConsultationLegalCaseService externalConsultationLegalCaseService) {
        this.documentsRepository = documentsRepository;
        this.legalCaseRepository = legalCaseRepository;
        this.externalFollowUpLegalCaseService = externalFollowUpLegalCaseService;
        this.externalConsultationLegalCaseService = externalConsultationLegalCaseService;
    }

    @Override
    public void handle(AddDocumentByLegalCaseIdCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());

        if (legalCase.isEmpty()) {
            throw new IllegalArgumentException("Legal case not found");
        }

        var document = new DocumentsItem(command, legalCase.get());

        var consultationResource = externalConsultationLegalCaseService
                .getConsultationResourceById(legalCase.get().getConsultationId());

        if (consultationResource.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        legalCase.get().getDocuments().addDocumentItem(document);

        externalFollowUpLegalCaseService.createNotification(
                "Document added to legal case",
                "Document " + document.getTitle() +
                        "\n added to legal case " + document.getDescription() +
                        "\n ype: " + document.getType() +
                        "\n Status: " + document.getStatus(),
                consultationResource.get().clientId(),
                consultationResource.get().id()
        );

        documentsRepository.save(document);
    }

    @Override
    public void handle(ChangeDocumentStatusCommand command) {
        var document = documentsRepository.findById(command.documentId());
        if (document.isEmpty()) {
            throw new IllegalArgumentException("Document not found");
        }
        document.get().setStatus(command.status());

        var legalCase = legalCaseRepository.findById(document.get().getLegalCase().getId());
        if (legalCase.isEmpty()) {
            throw new IllegalArgumentException("Legal case not found");
        }

        var consultationResource = externalConsultationLegalCaseService
                .getConsultationResourceById(legalCase.get().getConsultationId());

        if (consultationResource.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }

        externalFollowUpLegalCaseService.createNotification(
                "Document status changed",
                "Document " + document.get().getTitle() +
                        "\n status changed to " + document.get().getStatus(),
                consultationResource.get().clientId(),
                consultationResource.get().id()
        );

        documentsRepository.save(document.get());
    }
}
