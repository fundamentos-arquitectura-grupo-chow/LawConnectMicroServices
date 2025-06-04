package org.lorem.followupservice.domain.model.aggregates;

import jakarta.persistence.Entity;
import lombok.Getter;
import org.lorem.followupservice.domain.model.commands.CreateNotificationCommand;

@Getter
@Entity
public class Notification extends AuditableAbstractAggregateRoot<Notification> {

    private String title;

    private String description;

    private Long clientId;

    private Long consultationId;

    public Notification(CreateNotificationCommand command) {
        this.title = command.title();
        this.description = command.description();
        this.clientId = command.clientId();
        this.consultationId = command.legalCaseId();
    }

    public Notification() {

    }
}
