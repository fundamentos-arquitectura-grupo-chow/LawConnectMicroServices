package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.CreateClientCommand;
import org.lorem.profilesservice.interfaces.rest.resources.CreateClientResource;

public class CreateClientCommandFromResourceAssembler {
    public static CreateClientCommand ToCommandFromResource(CreateClientResource resource) {
        return new CreateClientCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.phoneNumber(),
                resource.address(),
                resource.dni(),
                resource.image_url()
        );
    }
}
