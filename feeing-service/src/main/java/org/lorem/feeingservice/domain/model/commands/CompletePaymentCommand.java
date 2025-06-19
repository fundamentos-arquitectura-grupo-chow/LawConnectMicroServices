package org.lorem.feeingservice.domain.model.commands;

import java.time.LocalDate;

public record CompletePaymentCommand(
        Long paymentId,
        String cardNumber,
        LocalDate expirationDate,
        String cvv
) {
}
