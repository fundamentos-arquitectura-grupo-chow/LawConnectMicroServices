package org.lorem.communicationservice.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.communicationservice.internal.outboundServices.ExternalConsultationCommunicationService;
import org.lorem.communicationservice.internal.outboundServices.ExternalFollowUpCommunicationService;
import org.lorem.communicationservice.internal.outboundServices.ExternalPaymentCommunicationService;
import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;
import org.lorem.communicationservice.domain.services.AppointmentCommandService;
import org.lorem.communicationservice.repositories.AppointmentRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    private final ExternalConsultationCommunicationService externalConsultationCommunicationService;
    private final ExternalFollowUpCommunicationService externalFollowUpCommunicationService;
    private final ExternalPaymentCommunicationService externalPaymentCommunicationService;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, ExternalConsultationCommunicationService externalConsultationCommunicationService, ExternalFollowUpCommunicationService externalFollowUpCommunicationService, ExternalPaymentCommunicationService externalPaymentCommunicationService) {
        this.appointmentRepository = appointmentRepository;
        this.externalConsultationCommunicationService = externalConsultationCommunicationService;
        this.externalFollowUpCommunicationService = externalFollowUpCommunicationService;
        this.externalPaymentCommunicationService = externalPaymentCommunicationService;
    }

    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {

        var consultation = externalConsultationCommunicationService.getConsultationById(command.consultationId());

        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation not found");
        }
        var appointment = new Appointment(command, consultation.get());

        appointmentRepository.save(appointment);

        var message = command.description() + " - " + command.location();

        externalFollowUpCommunicationService.createNotification(
                "Appointment created",
                message,
                command.consultationId(),
                command.profileId()
        );

        return Optional.of(appointment);
    }
}
