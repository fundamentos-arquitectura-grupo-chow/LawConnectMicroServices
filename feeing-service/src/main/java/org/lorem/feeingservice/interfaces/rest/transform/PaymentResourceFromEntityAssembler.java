package org.lorem.feeingservice.interfaces.rest.transform;

import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity){
        return new PaymentResource(
                entity.getId(),
                entity.getClientId(),
                entity.getAmount().paymentAmountToString(),
                entity.getStatus(),
                entity.getConsultation()
        );
    }
}
