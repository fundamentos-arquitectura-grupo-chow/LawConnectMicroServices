package org.lorem.consultationservice.interfaces.rest.transform;

import org.lorem.consultationservice.domain.model.aggregates.Consultation;
import org.lorem.consultationservice.interfaces.rest.resources.ConsultationResource;
import upc.LoremIpsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;

public class ConsultationResourceFromEntityAssembler {
    public static ConsultationResource toResourceFromEntity(Consultation entity, List<PaymentResource> paymentResource) {
        return new ConsultationResource(
                entity.getId(),
                entity.getLawyerId(),
                entity.getClientId(),
                paymentResource,
                entity.getDescription(),
                entity.getConsultationType().toString(),
                entity.getApplicationStatus().toString()
        );
    }
}
