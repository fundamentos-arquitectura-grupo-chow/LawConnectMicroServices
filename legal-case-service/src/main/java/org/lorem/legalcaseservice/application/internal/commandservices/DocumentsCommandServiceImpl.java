package org.lorem.legalcaseservice.application.internal.commandservices;

import org.lorem.legalcaseservice.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import org.lorem.legalcaseservice.domain.model.commands.ChangeDocumentStatusCommand;
import org.lorem.legalcaseservice.domain.model.entities.DocumentsItem;
import org.lorem.legalcaseservice.domain.services.DocumentsCommandService;
import org.lorem.legalcaseservice.infrastructure.grpc.ConsultationGrpcClient;
import org.lorem.legalcaseservice.infrastructure.grpc.FollowUpGrpcClient;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.DocumentsRepository;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentsCommandServiceImpl implements DocumentsCommandService {

    private final DocumentsRepository documentsRepository;
    private final LegalCaseRepository legalCaseRepository;
    private final FollowUpGrpcClient followUpGrpcClient;
    private final ConsultationGrpcClient consultationGrpcClient;

    public DocumentsCommandServiceImpl(DocumentsRepository documentsRepository, LegalCaseRepository legalCaseRepository, FollowUpGrpcClient followUpGrpcClient, ConsultationGrpcClient consultationGrpcClient) {
        this.documentsRepository = documentsRepository;
        this.legalCaseRepository = legalCaseRepository;
        this.followUpGrpcClient = followUpGrpcClient;
        this.consultationGrpcClient = consultationGrpcClient;
    }

    @Override
    public void handle(AddDocumentByLegalCaseIdCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());

        if (legalCase.isEmpty()) {
            throw new IllegalArgumentException("Legal case not found");
        }
        var document = new DocumentsItem(command, legalCase.get());

        legalCase.get().getDocuments().addDocumentItem(document);

        followUpGrpcClient.createNotification(
                "Document added to legal case",
                "Document " + document.getTitle() +
                        "\n added to legal case " + document.getDescription() +
                        "\n Type: " + document.getType() +
                        "\n Status: " + document.getStatus(),
                consultationGrpcClient.getClientIdByConsultationId(legalCase.get().getConsultationId()),
                legalCase.get().getConsultationId()
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

        followUpGrpcClient.createNotification(
                "Document status changed",
                "Document " + document.get().getTitle() +
                        "\n status changed to " + document.get().getStatus(),
                consultationGrpcClient.getClientIdByConsultationId(legalCase.get().getConsultationId()),
                legalCase.get().getConsultationId()
        );

        documentsRepository.save(document.get());
    }
}
