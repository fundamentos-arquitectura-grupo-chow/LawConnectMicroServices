package org.lorem.consultationservice.interfaces.rest.transform;

import org.lorem.consultationservice.domain.model.commands.CreatePaymentByConsultationIdCommand;
import org.lorem.consultationservice.interfaces.rest.resources.AddPaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentByConsultationIdCommand toCommandFromResource(AddPaymentResource resource){
        return new CreatePaymentByConsultationIdCommand(
                resource.consultationId(),
                resource.amount(),
                resource.currency()
        );
    }
}
