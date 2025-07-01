package org.lorem.communicationservice.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.application.internal.outboundServices.ExternalConsultationCommunicationService;
import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;
import org.lorem.communicationservice.domain.services.AppointmentQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService) {
        this.appointmentRepository = appointmentRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByConsultationIdQuery query) {
        var consultation = externalConsultationCommunicationService.getConsultationById(query.consultationId());
        return appointmentRepository.findAllByConsultation(consultation.get());
    }
}
