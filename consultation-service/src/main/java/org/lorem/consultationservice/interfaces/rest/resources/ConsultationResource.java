package org.lorem.consultationservice.interfaces.rest.resources;

//import org.lorem.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Long clientId,
        //List<PaymentResource> payment,
        String description,
        String consultationType,
        String applicationStatus
) {
}
