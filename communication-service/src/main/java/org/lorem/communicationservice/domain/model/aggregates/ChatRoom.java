package org.lorem.communicationservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.lorem.communicationservice.domain.model.valueobjects.CommunicationStatus;
import org.lorem.communicationservice.domain.model.valueobjects.Messages;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Entity
@Getter
public class ChatRoom extends AuditableAbstractAggregateRoot<ChatRoom> {

    @JoinColumn(name = "consultation", nullable = false)
    private Long consultationId;

    @Embedded
    private final Messages messages;

    public ChatRoom() {
        this.messages = new Messages();
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public ChatRoom(Long consultationId) {
        this();
        this.consultationId = consultationId;
        this.status = CommunicationStatus.PENDING;
    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }

}
