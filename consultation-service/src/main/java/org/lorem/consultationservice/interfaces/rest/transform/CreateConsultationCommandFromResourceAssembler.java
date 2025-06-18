package org.lorem.consultationservice.interfaces.rest.transform;

import org.lorem.consultationservice.domain.model.commands.CreateConsultationCommand;
import org.lorem.consultationservice.interfaces.rest.resources.CreateConsultationResource;

public class CreateConsultationCommandFromResourceAssembler {
    public static CreateConsultationCommand toCommandFromResource(CreateConsultationResource resource){
        return new CreateConsultationCommand(
                resource.lawyerId(),
                resource.clientId(),
                resource.description(),
                resource.Currency(),
                resource.type(),
                resource.title()
        );
    }
}
