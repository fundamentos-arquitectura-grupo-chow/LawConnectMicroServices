package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.IncrementPaidServicesCommand;
import org.lorem.profilesservice.interfaces.rest.resources.IncrementPaidServicesResource;

public class IncrementPaidServicesCommandFromResourceAssembler {
    public static IncrementPaidServicesCommand ToCommandFromResource(IncrementPaidServicesResource resource){
        return new IncrementPaidServicesCommand(resource.id());
    }
}
