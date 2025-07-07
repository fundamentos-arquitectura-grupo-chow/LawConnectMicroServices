package org.lorem.communicationservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.lorem.communicationservice.domain.model.commands.CreateVideoCallCommand;
import org.lorem.communicationservice.domain.model.valueobjects.CommunicationStatus;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class VideoCall extends AuditableAbstractAggregateRoot<VideoCall> {

    @JoinColumn(name = "consultation", nullable = false)
    private Long consultationId;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public VideoCall(CreateVideoCallCommand command) {
        this.description = command.description();
        this.status = CommunicationStatus.PENDING;
        this.consultationId = command.consultationId();
    }

    public VideoCall() {

    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }
}
