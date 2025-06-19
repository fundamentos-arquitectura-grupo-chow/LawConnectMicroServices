package org.lorem.consultationservice.interfaces.rest.resources;

public record AddPaymentResource(
        Long consultationId,
        Double amount,
        Integer currency
) {
}
