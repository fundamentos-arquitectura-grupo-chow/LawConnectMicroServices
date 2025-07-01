package org.lorem.feeingservice.interfaces.rest.resources;

public record CompletePaymentResource(
        String cardNumber,
        String expirationDate,
        String cvv
) {
}
