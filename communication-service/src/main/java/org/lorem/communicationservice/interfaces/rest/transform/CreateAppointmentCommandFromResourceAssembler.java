package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;
import org.lorem.communicationservice.interfaces.rest.resources.CreateAppointmentResource;

public class CreateAppointmentCommandFromResourceAssembler {
    public static CreateAppointmentCommand toCommandFromResource(CreateAppointmentResource resource){
        return new CreateAppointmentCommand(
                resource.consultationId(),
                resource.description(),
                resource.location()
        );
    }
}
