/*
package org.lorem.legalcaseservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.commands.CloseLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.DeleteLegalCaseCommand;
import org.lorem.legalcaseservice.domain.services.LegalCaseCommandService;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;

import java.util.Optional;

@Service
public class LegalCaseCommandServiceImpl implements LegalCaseCommandService {

    private final LegalCaseRepository legalCaseRepository;
    private final ExternalConsultationLegalCaseService externalConsultationLegalCaseService;

    public LegalCaseCommandServiceImpl(LegalCaseRepository legalCaseRepository, ExternalConsultationLegalCaseService externalConsultationLegalCaseService) {
        this.legalCaseRepository = legalCaseRepository;
        this.externalConsultationLegalCaseService = externalConsultationLegalCaseService;
    }

    @Override
    public Optional<LegalCase> handle(CreateLegalCaseCommand command) {
        var consultationResource = externalConsultationLegalCaseService.getConsultationResourceById(command.consultationId());

        if (consultationResource.isEmpty()) {
            return Optional.empty();
        }

        var legalCase = new LegalCase(command);
        legalCaseRepository.save(legalCase);

        return Optional.of(legalCase);
    }

    @Override
    public void handle(CloseLegalCaseCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());
        legalCase.ifPresent(lc -> {
            lc.close();
            legalCaseRepository.save(lc);
        });
    }

    @Override
    public void handle(DeleteLegalCaseCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());
        legalCase.ifPresent(legalCaseRepository::delete);
    }
}*/
