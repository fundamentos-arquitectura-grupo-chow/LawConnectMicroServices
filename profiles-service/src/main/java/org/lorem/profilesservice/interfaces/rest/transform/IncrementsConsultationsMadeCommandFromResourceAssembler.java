package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.commands.IncrementConsultationsMadeCommand;
import org.lorem.profilesservice.interfaces.rest.resources.IncrementConsultationsMadeResource;

public class IncrementsConsultationsMadeCommandFromResourceAssembler {
    public static IncrementConsultationsMadeCommand ToCommandFromResource(IncrementConsultationsMadeResource resource){
        return new IncrementConsultationsMadeCommand(
                resource.id()
        );
    }
}
