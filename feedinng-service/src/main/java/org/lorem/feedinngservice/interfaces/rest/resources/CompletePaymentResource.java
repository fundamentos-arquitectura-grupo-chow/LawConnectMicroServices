package org.lorem.feedinngservice.interfaces.rest.resources;

public record CompletePaymentResource(
        String cardNumber,
        String expirationDate,
        String cvv
) {
}
