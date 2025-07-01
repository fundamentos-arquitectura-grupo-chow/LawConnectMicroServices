package org.lorem.communicationservice.interfaces.rest.transform;

import org.lorem.communicationservice.domain.model.commands.CreateVideoCallCommand;
import org.lorem.communicationservice.interfaces.rest.resources.CreateVideoCallResource;

public class CreateVideoCallCommandFromResourceAssembler {
    public static CreateVideoCallCommand toCommandFromResource(CreateVideoCallResource resource){
        return new CreateVideoCallCommand(
                resource.consultationId(),
                resource.description()
        );
    }
}
