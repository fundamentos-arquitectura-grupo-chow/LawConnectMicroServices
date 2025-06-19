package org.lorem.feeingservice.domain.model.aggregates;

import org.lorem.feeingservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feeingservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feeingservice.domain.model.events.PaymentCompletedEvent;
import org.lorem.feeingservice.domain.model.valueObjects.*;
import org.lorem.feeingservice.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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