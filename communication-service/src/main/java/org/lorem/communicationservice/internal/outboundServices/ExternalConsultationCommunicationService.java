package org.lorem.communicationservice.application.internal.outboundServices;

import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.lorem.consultation.interfaces.acl.ConsultationContextFacade;
import org.lorem.consultation.interfaces.rest.resources.ConsultationResource;
import org.lorem.feeing.interfaces.rest.resources.PaymentResource;
import org.springframework.stereotype.Service;

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
