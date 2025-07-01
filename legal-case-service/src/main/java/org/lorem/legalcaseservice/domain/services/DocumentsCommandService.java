package org.lorem.legalcaseservice.domain.services;

import org.lorem.legalcaseservice.domain.model.commands.AddDocumentByLegalCaseIdCommand;
import org.lorem.legalcaseservice.domain.model.commands.ChangeDocumentStatusCommand;
import org.springframework.stereotype.Service;

@Service
public interface DocumentsCommandService {
    void handle(AddDocumentByLegalCaseIdCommand command);
    void handle(ChangeDocumentStatusCommand command);
}
