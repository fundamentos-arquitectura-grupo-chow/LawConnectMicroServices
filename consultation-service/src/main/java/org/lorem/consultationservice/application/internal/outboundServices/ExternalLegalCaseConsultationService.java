package org.lorem.consultationservice.application.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.legalcase.interfaces.acl.LegalCaseContextFacade;
import upc.LoremIpsum.lawconnectplatform.legalcase.interfaces.rest.resources.LegalCaseResource;

import java.util.Optional;

@Service
public class ExternalLegalCaseConsultationService {

    private final LegalCaseContextFacade legalCaseContextFacade;

    public ExternalLegalCaseConsultationService(LegalCaseContextFacade legalCaseContextFacade) {
        this.legalCaseContextFacade = legalCaseContextFacade;
    }

    public void createLegalCase(
            String title,
            String description,
            Long consultationId
    ){
        legalCaseContextFacade.createLegalCase(
                title,
                description,
                consultationId
        );
    }

    public Optional<LegalCaseResource> getLegalCaseByConsultationId(Long consultationId){
        return legalCaseContextFacade.getLegalCaseByConsultationId(consultationId);
    }

    public void deleteLegalCaseById(Long legalCaseId) {
        legalCaseContextFacade.deleteLegalCase(legalCaseId);
    }
}