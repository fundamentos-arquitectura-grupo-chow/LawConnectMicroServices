/*
package org.lorem.legalcaseservice.interfaces.acl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.lorem.legalcaseservice.application.internal.outboundServices.ExternalConsultationLegalCaseService;
import org.lorem.legalcaseservice.domain.model.commands.CreateLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.commands.DeleteLegalCaseCommand;
import org.lorem.legalcaseservice.domain.model.queries.GetLegalCaseByConsultationIdQuery;
import org.lorem.legalcaseservice.domain.services.LegalCaseCommandService;
import org.lorem.legalcaseservice.domain.services.LegalCaseQueryService;
import org.lorem.legalcaseservice.interfaces.rest.resources.LegalCaseResource;
import org.lorem.legalcaseservice.interfaces.rest.transform.LegalCaseResourceFromEntityAssembler;

import java.util.Optional;

@Service
public class LegalCaseContextFacade {

    private final LegalCaseCommandService legalCaseCommandService;
    private final LegalCaseQueryService legalCaseQueryService;
    private final ExternalConsultationLegalCaseService externalConsultationLegalCaseService;

    public LegalCaseContextFacade(
            @Lazy LegalCaseCommandService legalCaseCommandService,
            @Lazy LegalCaseQueryService legalCaseQueryService,
            ExternalConsultationLegalCaseService externalConsultationLegalCaseService
    ) {
        this.legalCaseCommandService = legalCaseCommandService;
        this.legalCaseQueryService = legalCaseQueryService;
        this.externalConsultationLegalCaseService = externalConsultationLegalCaseService;
    }

    public Optional<LegalCaseResource> createLegalCase(
            String title,
            String description,
            Long consultationId
    ){
        var legalCase = legalCaseCommandService.handle(new CreateLegalCaseCommand(
                title,
                description,
                consultationId
        ));

        if (legalCase.isEmpty()) {
            return Optional.empty();
        }

        var consultationResource = externalConsultationLegalCaseService
                .getConsultationResourceById(consultationId);

        if (consultationResource.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(LegalCaseResourceFromEntityAssembler.toResourceFromEntity(legalCase.get()));
    }

    public Optional<LegalCaseResource> getLegalCaseByConsultationId(Long consultationId){
        var legalCase = legalCaseQueryService.handle(
                new GetLegalCaseByConsultationIdQuery(consultationId)
        );

        if (legalCase.isEmpty()) {
            return Optional.empty();
        }

        var consultationResource = externalConsultationLegalCaseService
                .getConsultationResourceById(consultationId);

        if (consultationResource.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(LegalCaseResourceFromEntityAssembler
                .toResourceFromEntity(legalCase.get()));
        }

    public void deleteLegalCase(Long legalCaseId){
        legalCaseCommandService.handle(new DeleteLegalCaseCommand(legalCaseId));
    }
}
*/
