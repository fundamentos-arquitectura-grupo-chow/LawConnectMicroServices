package org.lorem.communicationservice.domain.model.aggregates;

import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;
import org.lorem.communicationservice.domain.model.valueobjects.CommunicationStatus;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Appointment extends AuditableAbstractAggregateRoot<Appointment> {

    @OneToOne
    @JoinColumn(name = "consultation", nullable = false)
    private Consultation consultation;

    private String description;

    private String location;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public Appointment(CreateAppointmentCommand command, Consultation consultation) {
        this.consultation = consultation;
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
