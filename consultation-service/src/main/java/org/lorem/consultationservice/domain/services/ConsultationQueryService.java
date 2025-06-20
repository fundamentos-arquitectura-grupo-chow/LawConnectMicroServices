package org.lorem.consultationservice.domain.services;

import org.lorem.consultationservice.domain.model.aggregates.Consultation;
import org.lorem.consultationservice.domain.model.queries.*;
//import org.lorem.profilesservice.feeing.domain.model.aggregates.Payment;

import java.util.List;
import java.util.Optional;

public interface ConsultationQueryService {
    List<Consultation> handle(GetAllConsultationsByLawyerIdQuery query);
    Optional<Consultation> handle(GetConsultationByIdQuery query);
    Optional<Consultation> handle(GetConsultationByLawyerIdAndPaymentIdQuery query);
    Optional<Consultation> handle(GetConsultationByPaymentIdQuery query);
    //Optional<List<Payment>> handle(GetAllPaymentsByConsultationIdQuery query);
    List<Consultation> handle(GetAllConsultationsByClientIdQuery query);
    List<Consultation> handle(GetAllConsultationsByClientIdAndLawyerIdQuery query);
    List<Consultation> handle(GetAllConsultationsQuery query);
}
