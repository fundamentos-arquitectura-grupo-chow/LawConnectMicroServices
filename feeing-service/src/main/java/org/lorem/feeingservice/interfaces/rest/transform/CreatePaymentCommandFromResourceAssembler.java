package org.lorem.feeingservice.interfaces.rest.transform;

import org.lorem.feeingservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feeingservice.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.consultationId(),
                resource.clientId(),
                resource.amount(),
                resource.currency()
        );
    }
}
