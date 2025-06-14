/*
package org.lorem.legalcaseservice.application.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import upc.LoremIpsum.lawconnectplatform.consultation.interfaces.acl.ConsultationContextFacade;
import upc.LoremIpsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

import java.util.Optional;

@Service
public class ExternalConsultationLegalCaseService {

    private final ConsultationContextFacade consultationContextFacade;

    public ExternalConsultationLegalCaseService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }

    public Optional<ConsultationResource> getConsultationResourceById(Long consultationId) {
        Optional<Consultation> consultationOptional = consultationContextFacade.getConsultationById(consultationId);

        if (consultationOptional.isPresent()) {
            return consultationContextFacade.createConsultationResource(consultationOptional.get());
        }
        return Optional.empty();
    }
}*/
