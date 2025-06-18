package org.lorem.consultationservice.interfaces.rest.resources;

import upc.LoremIpsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;

public record ConsultationResource(
        Long id,
        Long lawyerId,
        Long clientId,
        List<PaymentResource> payment,
        String description,
        String consultationType,
        String applicationStatus
) {
}
