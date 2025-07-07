package org.lorem.communicationservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;
import org.lorem.communicationservice.domain.services.AppointmentQueryService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentQueryServiceImpl implements AppointmentQueryService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentQueryServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public List<Appointment> handle(GetAllAppointmentsByConsultationIdQuery query) {
        return appointmentRepository.findAllByConsultationId(query.consultationId());
    }
}
