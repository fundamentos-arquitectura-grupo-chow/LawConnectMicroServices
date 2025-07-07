package org.lorem.consultationservice.interfaces.acl;

import org.springframework.stereotype.Service;
//import org.lorem.consultationservice.application.internal.outboundServices.ExternalPaymentConsultationServices;
import org.lorem.consultationservice.domain.model.aggregates.Consultation;
import org.lorem.consultationservice.domain.model.commands.CompletePaymentByIdCommand;
import org.lorem.consultationservice.domain.model.queries.GetConsultationByIdQuery;
import org.lorem.consultationservice.domain.model.queries.GetConsultationByPaymentIdQuery;
import org.lorem.consultationservice.domain.services.ConsultationCommandService;
import org.lorem.consultationservice.domain.services.ConsultationQueryService;
import org.lorem.consultationservice.interfaces.rest.resources.ConsultationResource;
//import org.lorem.profilesservice.feeing.domain.model.aggregates.Payment;

import java.util.Optional;

@Service
public class ConsultationContextFacade {

    private final ConsultationCommandService consultationCommandService;
    private final ConsultationQueryService consultationQueryService;
    //private final ExternalPaymentConsultationServices externalPaymentConsultationServices;

    public ConsultationContextFacade(ConsultationCommandService consultationCommandService, ConsultationQueryService consultationQueryService
            //, ExternalPaymentConsultationServices externalPaymentConsultationServices
    ) {
        this.consultationCommandService = consultationCommandService;
        this.consultationQueryService = consultationQueryService;
        //this.externalPaymentConsultationServices = externalPaymentConsultationServices;
    }
    public Optional<Consultation> getConsultationById(Long consultationId){
        return consultationQueryService.handle(new GetConsultationByIdQuery(consultationId));
    }

    public void changeConsultationStatus(Long consultationId){
        consultationCommandService.handle(new CompletePaymentByIdCommand(consultationId));
    }

    public Optional<Consultation> getConsultationByPaymentId(Long paymentId){
        return consultationQueryService.handle(new GetConsultationByPaymentIdQuery(paymentId));
    }

    /*public Optional<List<Payment>> getAllPaymentsByConsultationId(Long consultationId){
        return consultationQueryService.handle(new GetAllPaymentsByConsultationIdQuery(consultationId));
    }*/

    public Optional<ConsultationResource> createConsultationResource(Consultation consultation){
        // Obtener pagos mediante el servicio externo en lugar de consultation.getPayments()
        //var payments = externalPaymentConsultationServices.getPaymentsByConsultationId(consultation.getId());
        //var paymentsResource = externalPaymentConsultationServices.createPaymentListResource(payments);

        //var consultationResource = ConsultationResourceFromEntityAssembler.toResourceFromEntity(consultation, paymentsResource);
        //return Optional.of(consultationResource);
        return Optional.empty();
    }

    public Long getClientIdById(Long consultationId) {
        var consultation = consultationQueryService.handle(new GetConsultationByIdQuery(consultationId));
        return consultation.get().getClientId();
    }

    public boolean existConsultationById(Long consultationId) {
        return consultationQueryService.handle(new GetConsultationByIdQuery(consultationId)).isPresent();
    }
}
