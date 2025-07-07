package org.lorem.consultationservice.interfaces.rest.transform;

import org.lorem.consultationservice.domain.model.aggregates.Consultation;
import org.lorem.consultationservice.interfaces.rest.resources.ConsultationResource;
//import org.lorem.profilesservice.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;

public class ConsultationResourceFromEntityAssembler {
    public static ConsultationResource toResourceFromEntity(Consultation entity
            //, List<PaymentResource> paymentResource
    ) {
        return new ConsultationResource(
                entity.getId(),
                entity.getLawyerId(),
                entity.getClientId(),
                entity.getDescription(),
                entity.getConsultationType().toString(),
                entity.getApplicationStatus().toString()
        );
    }
}
