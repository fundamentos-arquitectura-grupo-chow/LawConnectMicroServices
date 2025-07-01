package org.lorem.legalcaseservice.domain.model.aggregates;

import jakarta.persistence.*;
import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.valueobjects.Documents;
import org.lorem.legalcaseservice.domain.model.valueobjects.LegalCaseStatus;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class LegalCase extends AuditableAbstractAggregateRoot<LegalCase> {

    @Column(nullable = false)
    @Size(max = 120)
    private String title;

    @Column(nullable = false)
    @Size(max = 500)
    private String description;

    @Column(nullable = false)
    private LegalCaseStatus status;

    private Long consultationId;

    @Embedded
    private Documents documents;

    protected LegalCase() {
        this.documents = new Documents();
        this.status = LegalCaseStatus.OPEN;
    }


    public LegalCase(CreateLegalCaseCommand command) {
        this();
        this.title = command.title();
        this.description = command.description();
        this.consultationId = command.consultationId();
    }

    public void close() {
        this.status = LegalCaseStatus.CLOSED;
    }

}