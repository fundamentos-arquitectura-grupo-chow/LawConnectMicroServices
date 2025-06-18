package org.lorem.feedinngservice.domain.services;

import org.lorem.feedinngservice.domain.model.aggregates.Payment;
import org.lorem.feedinngservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feedinngservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feedinngservice.domain.model.commands.DeletePaymentCommand;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
    Optional<Payment> handle(CompletePaymentCommand command);
    void handle(DeletePaymentCommand command);
}
