package org.lorem.feeingservice.interfaces.rest.resources;

import org.lorem.feeingservice.domain.model.valueObjects.PaymentStatus;

public record PaymentResource(
        Long id,
        Long clientId,
        String amount,
        PaymentStatus status,
        Long consultationId
){

}
