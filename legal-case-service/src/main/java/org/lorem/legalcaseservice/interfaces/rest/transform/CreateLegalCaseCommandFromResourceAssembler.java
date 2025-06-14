package org.lorem.legalcaseservice.interfaces.rest.transform;

import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.interfaces.rest.resources.CreateLegalCaseResource;

public class CreateLegalCaseCommandFromResourceAssembler {
    public static CreateLegalCaseCommand toCommandFromResource(CreateLegalCaseResource resource) {
        return new CreateLegalCaseCommand(
                resource.title(),
                resource.description(),
                resource.consultationId()
        );
    }
}
