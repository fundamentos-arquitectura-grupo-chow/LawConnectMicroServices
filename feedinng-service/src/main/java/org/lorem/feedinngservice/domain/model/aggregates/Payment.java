package org.lorem.feedinngservice.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.lorem.feedinngservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feedinngservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feedinngservice.domain.model.events.PaymentCompletedEvent;
import org.lorem.feedinngservice.domain.model.valueObjects.*;
import org.lorem.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @Column(name = "consultation_id")
    private Long consultationId;

    private Long clientId;

    @Embedded
    private PaymentAmount amount;

    @Setter
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Embedded
    private Card card;

    public Payment(CreatePaymentCommand command) {
        this();
        this.consultationId = command.consultationId();
        this.amount = new PaymentAmount(
                command.amount(),
                Currency.fromId(command.currency())
        );
        this.clientId = command.clientId();
        this.status = PaymentStatus.PENDIENTE;
        this.card = new Card();
    }

    public Payment() {}

    public void updateCard(CompletePaymentCommand command) {
        this.card = new Card(command.cardNumber(), command.expirationDate(), command.cvv());
    }

    public void finishProject() {
        this.registerEvent(new PaymentCompletedEvent(this, this.getId()));
    }
}