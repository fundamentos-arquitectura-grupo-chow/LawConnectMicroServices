package org.lorem.consultationservice.domain.model.commands;

import java.time.LocalDate;

public record CompletePaymentByIdCommand(
        Long consultationId,
        Long paymentId,
        String cardNumber,
        LocalDate expirationDate,
        String cvv
) {
}
