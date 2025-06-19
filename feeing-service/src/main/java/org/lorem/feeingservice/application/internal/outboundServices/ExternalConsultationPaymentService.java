package org.lorem.feeingservice.application.internal.outboundServices;

/*import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;
import upc.LoremIpsum.lawconnectplatform.consultation.interfaces.acl.ConsultationContextFacade;*/
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalConsultationPaymentService {

   /* private final ConsultationContextFacade consultationContextFacade;

    public ExternalConsultationPaymentService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }
*/
    public boolean existsConsultationById(Long consultationId) {
//        return consultationContextFacade.getConsultationById(consultationId).isPresent();
        return true;
    }
/*
    public void changeConsultationStatus(Long consultationId) {
        consultationContextFacade.changeConsultationStatus(consultationId);
    }

    public Optional<Consultation> getConsultationByPaymentId(Long paymentId) {
        return consultationContextFacade.getConsultationByPaymentId(paymentId);
    }

    public Optional<Consultation> getConsultationById(Long consultationId) {
        return consultationContextFacade.getConsultationById(consultationId);
    }*/
}