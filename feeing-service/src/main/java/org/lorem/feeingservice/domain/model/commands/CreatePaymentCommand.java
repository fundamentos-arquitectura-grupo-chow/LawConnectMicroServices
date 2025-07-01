package org.lorem.feeingservice.domain.model.commands;

public record CreatePaymentCommand(
        Long consultationId,
        Long clientId,
        Double amount,
        Integer currency
) {
}
