package org.lorem.communicationservice.domain.services;


import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;

import java.util.Optional;

public interface AppointmentCommandService {
    Optional<Appointment> handle(CreateAppointmentCommand command);
}
