package org.lorem.feeingservice.interfaces.rest.resources;

public record CreatePaymentResource(
        Long consultationId,
        Long clientId,
        Double amount,
        Integer currency
) {
}
