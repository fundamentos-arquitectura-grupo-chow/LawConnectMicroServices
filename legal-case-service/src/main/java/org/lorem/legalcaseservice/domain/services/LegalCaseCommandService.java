package org.lorem.legalcaseservice.domain.services;

import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.commands.CloseLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.DeleteLegalCaseCommand;

import java.util.Optional;

@Service
public interface LegalCaseCommandService {
    Optional<LegalCase> handle(CreateLegalCaseCommand command);
    void handle(CloseLegalCaseCommand command);
    void handle(DeleteLegalCaseCommand command);
}
