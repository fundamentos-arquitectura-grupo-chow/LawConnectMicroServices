package org.lorem.legalcaseservice.interfaces.rest.transform;

import org.lorem.legalcaseservice.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import org.lorem.legalcaseservice.interfaces.rest.resources.AddDocumentByLegalCaseIdResource;

public class AddDocumentByLegalCaseIdCommandFromResourceAssembler {
    public static AddDocumentByLegalCaseIdCommand toCommandFromResource(AddDocumentByLegalCaseIdResource resource) {
        return new AddDocumentByLegalCaseIdCommand(
                resource.title(),
                resource.description(),
                resource.type(),
                resource.status(),
                resource.legalCaseId()
        );
    }
}
