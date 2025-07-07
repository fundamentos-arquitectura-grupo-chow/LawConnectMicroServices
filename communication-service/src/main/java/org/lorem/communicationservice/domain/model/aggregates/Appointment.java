package org.lorem.communicationservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;
import org.lorem.communicationservice.domain.model.valueobjects.CommunicationStatus;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;


@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @JoinColumn(name = "consultation_id", nullable = false)
    private Long consultationId;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public Appointment(CreateAppointmentCommand command) {
        this.consultationId = command.consultationId();
        this.description = command.description();
        this.location = command.location();
        this.status = CommunicationStatus.PENDING;
    }

    public Appointment() {

    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }
}
