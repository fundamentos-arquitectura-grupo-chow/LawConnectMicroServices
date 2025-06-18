package org.lorem.feedinngservice.domain.model.commands;

import java.time.LocalDate;

public record CompletePaymentCommand(
        Long paymentId,
        String cardNumber,
        LocalDate expirationDate,
        String cvv
) {
}
