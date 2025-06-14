package org.lorem.legalcaseservice.domain.services;

import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import org.lorem.legalcaseservice.domain.model.commands.ChangeDocumentStatusCommand;

@Service
public interface DocumentsCommandService {
    void handle(AddDocumentByLegalCaseIdCommand command);
    void handle(ChangeDocumentStatusCommand command);
}
