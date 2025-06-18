package org.lorem.feedinngservice.interfaces.rest.resources;

import org.lorem.feedinngservice.domain.model.valueObjects.PaymentStatus;

public record PaymentResource(
        Long id,
        Long clientId,
        String amount,
        PaymentStatus status,
        Long consultationId
){

}
