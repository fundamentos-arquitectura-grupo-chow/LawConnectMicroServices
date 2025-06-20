package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.CreateLawyerCommand;
import org.lorem.profilesservice.interfaces.rest.resources.CreateLawyerResource;

public class CreateLawyerCommandFromResourceAssembler {
    public static CreateLawyerCommand ToCommandFromResource(CreateLawyerResource resource)
    {
        return new CreateLawyerCommand(
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
