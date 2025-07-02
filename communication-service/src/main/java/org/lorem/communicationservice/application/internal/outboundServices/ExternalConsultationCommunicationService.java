package org.lorem.communicationservice.application.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import upc.LoremIpsum.lawconnectplatform.consultation.interfaces.acl.ConsultationContextFacade;
import upc.LoremIpsum.lawconnectplatform.consultation.interfaces.rest.resources.ConsultationResource;

import java.util.Optional;

@Service
public class ExternalConsultationCommunicationService {

    private final ConsultationContextFacade consultationContextFacade;

    public ExternalConsultationCommunicationService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }

    public Optional<Consultation> getConsultationById(Long consultationId){
        return consultationContextFacade.getConsultationById(consultationId);
    }

    public Optional<ConsultationResource> createConsultationResource(Consultation consultation){
        return consultationContextFacade.createConsultationResource(consultation);
    }
}
