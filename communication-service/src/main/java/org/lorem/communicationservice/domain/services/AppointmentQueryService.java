package org.lorem.communicationservice.domain.services;

import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.domain.model.queries.GetAllAppointmentsByConsultationIdQuery;

import java.util.List;

public interface AppointmentQueryService {
    List<Appointment> handle(GetAllAppointmentsByConsultationIdQuery query);
}
