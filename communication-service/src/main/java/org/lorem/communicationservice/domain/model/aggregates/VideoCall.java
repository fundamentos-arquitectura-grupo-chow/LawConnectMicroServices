package org.lorem.communicationservice.domain.model.aggregates;

import org.lorem.communicationservice.domain.model.commands.CreateVideoCallCommand;
import org.lorem.communicationservice.domain.model.valueobjects.CommunicationStatus;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.legalcase.domain.model.valueobjects.DocumentsStatus;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class VideoCall extends AuditableAbstractAggregateRoot<VideoCall> {

    @OneToOne
    @JoinColumn(name = "consultation", nullable = false)
    private Consultation consultation;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public VideoCall(CreateVideoCallCommand command, Consultation consultation) {
        this.description = command.description();
        this.status = CommunicationStatus.PENDING;
        this.consultation = consultation;
    }

    public VideoCall() {

    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }
}
