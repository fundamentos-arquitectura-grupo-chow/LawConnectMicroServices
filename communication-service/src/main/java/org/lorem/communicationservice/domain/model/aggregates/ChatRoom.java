package org.lorem.communicationservice.domain.model.aggregates;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;
import org.lorem.communicationservice.domain.model.commands.CreateChatRoomCommand;
import org.lorem.communicationservice.domain.model.valueobjects.CommunicationStatus;
import org.lorem.communicationservice.domain.model.valueobjects.Messages;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ChatRoom extends AuditableAbstractAggregateRoot<ChatRoom> {

    @OneToOne
    @JoinColumn(name = "consultation", nullable = false)
    private Consultation consultation;

    @Embedded
    private Messages messages;

    public ChatRoom() {
        this.messages = new Messages();
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CommunicationStatus status;

    public ChatRoom(Consultation consultation) {
        this();
        this.consultation = consultation;
        this.status = CommunicationStatus.PENDING;
    }

    public void setStatus(Integer status) {
        this.status = CommunicationStatus.fromId(status);
    }

}
