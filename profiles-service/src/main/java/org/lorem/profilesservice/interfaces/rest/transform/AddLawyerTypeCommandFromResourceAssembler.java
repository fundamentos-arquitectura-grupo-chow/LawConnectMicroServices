package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.AddLawyerTypeCommand;
import org.lorem.profilesservice.interfaces.rest.resources.AddLawyerTypeResource;

public class AddLawyerTypeCommandFromResourceAssembler {
    public static AddLawyerTypeCommand ToCommandFromResource(AddLawyerTypeResource resource) {
        return new AddLawyerTypeCommand(
                resource.lawyerId(),
                resource.lawyerTypeId()
        );
    }
}
