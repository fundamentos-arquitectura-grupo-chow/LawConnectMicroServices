package org.lorem.legalcaseservice.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import org.lorem.legalcaseservice.domain.model.valueobjects.DocumentType;
import org.lorem.legalcaseservice.domain.model.valueobjects.DocumentsStatus;

@Setter
@Getter
@Entity
public class DocumentsItem extends AuditableModel {

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType type;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "legal_case_id")
    private LegalCase legalCase;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentsStatus status;

    public DocumentsItem(AddDocumentByLegalCaseIdCommand command, LegalCase legalCase) {
        this.title = command.title();
        this.type = DocumentType.fromId(command.type());
        this.description = command.description();
        this.status = DocumentsStatus.fromId(command.status());
        this.legalCase = legalCase;
    }

    public DocumentsItem() {

    }

    public void setStatus(Integer status) {
        this.status = DocumentsStatus.fromId(status);
    }
}
