package org.lorem.communicationservice.application.internal.commandservices;

import org.lorem.communicationservice.infrastructure.grpc.ConsultationGrpcClient;
import org.lorem.communicationservice.infrastructure.grpc.FollowUpGrpcClient;
import org.springframework.stereotype.Service;
import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.communicationservice.domain.model.commands.CreateAppointmentCommand;
import org.lorem.communicationservice.domain.services.AppointmentCommandService;
import org.lorem.communicationservice.infrastructure.persistence.jpa.repositories.AppointmentRepository;

import java.util.Optional;

@Service
public class AppointmentCommandServiceImpl implements AppointmentCommandService {

    private final AppointmentRepository appointmentRepository;
    private final FollowUpGrpcClient followUpGrpcClient;
    private final ConsultationGrpcClient consultationGrpcClient;

    public AppointmentCommandServiceImpl(AppointmentRepository appointmentRepository, FollowUpGrpcClient followUpGrpcClient, ConsultationGrpcClient consultationGrpcClient) {
        this.appointmentRepository = appointmentRepository;
        this.followUpGrpcClient = followUpGrpcClient;
        this.consultationGrpcClient = consultationGrpcClient;
    }


    @Override
    public Optional<Appointment> handle(CreateAppointmentCommand command) {

        var profileId = consultationGrpcClient.getClientIdByConsultationId(command.consultationId());

        var appointment = new Appointment(command);

        appointmentRepository.save(appointment);

        var message = command.description() + " - " + command.location();

        followUpGrpcClient.createNotification(
                "Appointment created",
                message,
                command.consultationId(),
                profileId
        );

        return Optional.of(appointment);
    }
}
