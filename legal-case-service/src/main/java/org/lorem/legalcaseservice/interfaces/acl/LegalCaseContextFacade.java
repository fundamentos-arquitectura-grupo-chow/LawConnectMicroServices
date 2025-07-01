package org.lorem.legalcaseservice.interfaces.acl;

import org.lorem.legalcaseservice.domain.model.aggregates.LegalCase;
import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.DeleteLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import org.lorem.legalcaseservice.domain.services.LegalCaseCommandService;
import org.lorem.legalcaseservice.domain.services.LegalCaseQueryService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LegalCaseContextFacade {

    private final LegalCaseCommandService legalCaseCommandService;
    private final LegalCaseQueryService legalCaseQueryService;

    public LegalCaseContextFacade(
            @Lazy LegalCaseCommandService legalCaseCommandService,
            @Lazy LegalCaseQueryService legalCaseQueryService
    ) {
        this.legalCaseCommandService = legalCaseCommandService;
        this.legalCaseQueryService = legalCaseQueryService;
    }

    public void createLegalCase(
            String title,
            String description,
            Long consultationId
    ){
        legalCaseCommandService.handle(new CreateLegalCaseCommand(
                        title,
                        description,
                        consultationId
                )
        );
    }

    public Optional<LegalCase> getLegalCaseByConsultationId(Long consultationId){
        return legalCaseQueryService.handle(new GetLegalCaseByConsultationIdQuery(consultationId));
    }

    public void deleteLegalCase(Long legalCaseId){
        var consultation = legalCaseQueryService.handle(new GetLegalCaseByConsultationIdQuery(legalCaseId));
        legalCaseCommandService.handle(new DeleteLegalCaseCommand(consultation.get().getId()));
    }

}
