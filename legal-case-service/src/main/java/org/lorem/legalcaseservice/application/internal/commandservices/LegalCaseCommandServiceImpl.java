package org.lorem.legalcaseservice.application.internal.commandservices;

import org.lorem.legalcaseservice.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.commands.CloseLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.DeleteLegalCaseCommand;
import org.lorem.legalcaseservice.domain.services.LegalCaseCommandService;
import org.lorem.legalcaseservice.infrastructure.persistence.jpa.repositories.LegalCaseRepository;
import org.springframework.stereotype.Service;

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

        var consultation = externalConsultationLegalCaseService.getConsultationById(command.consultationId());

        var legalCase = new LegalCase(command);
        legalCaseRepository.save(legalCase);

        return Optional.of(legalCase);
    }

    @Override
    public void handle(CloseLegalCaseCommand command) {
            var legalCase = legalCaseRepository.findById(command.legalCaseId());
            legalCase.ifPresent(LegalCase::close);
            legalCaseRepository.save(legalCase.get());
    }

    @Override
    public void handle(DeleteLegalCaseCommand command) {
        var legalCase = legalCaseRepository.findById(command.legalCaseId());
        legalCase.ifPresent(legalCaseRepository::delete);
    }


}
