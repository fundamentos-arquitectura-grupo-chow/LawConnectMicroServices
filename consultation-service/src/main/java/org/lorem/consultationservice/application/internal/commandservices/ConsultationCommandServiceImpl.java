package org.lorem.consultationservice.application.internal.commandservices;

import org.lorem.consultationservice.infrastructure.kafka.CommunicationKafkaProducer;
import org.lorem.consultationservice.infrastructure.kafka.FeeingKafkaProducer;
import org.lorem.consultationservice.infrastructure.kafka.FollowUpKafkaProducer;
import org.lorem.consultationservice.infrastructure.kafka.LegalCaseKafkaProducer;
import org.springframework.stereotype.Service;
import org.lorem.consultationservice.domain.model.aggregates.Consultation;
import org.lorem.consultationservice.domain.model.commands.*;
import org.lorem.consultationservice.domain.services.ConsultationCommandService;
import org.lorem.consultationservice.infrastructure.persistence.jpa.repositories.ConsultationRepository;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {
    private final ConsultationRepository consultationRepository;
    private final CommunicationKafkaProducer communicationKafkaProducer;
    private final FollowUpKafkaProducer followUpKafkaProducer;
    private final FeeingKafkaProducer feeingKafkaProducer;
    private final LegalCaseKafkaProducer legalCaseKafkaProducer;

    public ConsultationCommandServiceImpl(ConsultationRepository consultationRepository, CommunicationKafkaProducer communicationKafkaProducer, FollowUpKafkaProducer followUpKafkaProducer, FeeingKafkaProducer feeingKafkaProducer, LegalCaseKafkaProducer legalCaseKafkaProducer) {
        this.consultationRepository = consultationRepository;
        this.communicationKafkaProducer = communicationKafkaProducer;
        this.followUpKafkaProducer = followUpKafkaProducer;
        this.feeingKafkaProducer = feeingKafkaProducer;
        this.legalCaseKafkaProducer = legalCaseKafkaProducer;
    }

    @Override
    public Long handle(CreateConsultationCommand command) {

        var consultation = new Consultation(command);

        try {
            consultationRepository.save(consultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving consultation: " + e.getMessage());
        }

        communicationKafkaProducer.createChatRoom(consultation.getId());
        legalCaseKafkaProducer.sendLegalCaseCreatedMessage(
                command.title(),command.description(), consultation.getId()
        );

        followUpKafkaProducer.createNotification(
                "Consulta Creada",
                "Se ha creado una nueva consulta con descripción: " + consultation.getDescription(),
                consultation.getClientId(),
                consultation.getId()
        );

        return consultation.getId();
    }

    @Override
    public void handle(DeleteConsultationCommand command) {
        if (!consultationRepository.existsById(command.consultationId())) {
            throw new IllegalArgumentException("Consultation");
        }
        try {
            consultationRepository.deleteById(command.consultationId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while deleting consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(CompletePaymentByIdCommand command) {
        var consultation = consultationRepository.findById(command.consultationId());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            feeingKafkaProducer.sendPaymentCompletedMessage(
                    command.paymentId(),
                    command.cardNumber(),
                    command.expirationDate(),
                    command.cvv()
            );

            followUpKafkaProducer.createNotification(
                    "Pago Aceptado",
                    "Ahora la consulta se encuentra pagada",
                    consultation.get().getClientId(),
                    command.consultationId()
            );
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while changing consultation status: " + e.getMessage());
        }
    }

    @Override
    public void handle(ApproveConsultationCommand command) {
        var consultation = consultationRepository.findById(command.consultationId());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            followUpKafkaProducer.createNotification(
                    "Consulta Aceptada" ,
                    "La consulta ha sido aceptada sobre " + consultation.get().getDescription(),
                    consultation.get().getClientId(),
                    command.consultationId()
            );
            consultation.get().setApplicationAccepted();
            consultationRepository.save(consultation.get());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while approving consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(RejectConsultationCommand command) {
        var consultation = consultationRepository.findById(command.consultationId());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            followUpKafkaProducer.createNotification(
                    "Consulta Rechazada",
                    "La consulta ha sido rechazada",
                    consultation.get().getClientId(),
                    command.consultationId()
            );
            consultation.get().setApplicationDenied();

            communicationKafkaProducer.deleteChatRoom(consultation.get().getId());
            legalCaseKafkaProducer.sendLegalCaseDeletedMessage(consultation.get().getId());
            consultationRepository.deleteById(consultation.get().getId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while rejecting consultation: " + e.getMessage());
        }
    }

    @Override
    public void handle(CreatePaymentByConsultationIdCommand command) {
        var consultation = consultationRepository.findById(command.consultationId());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            feeingKafkaProducer.sendPaymentCreatedMessage(
                    consultation.get().getId(),
                    consultation.get().getClientId(),
                    command.amount(),
                    command.currency()
            );
            System.out.println("Payment created");
            // No es necesario llamar a addPayment porque la asociación se hace
            // mediante el consultationId en el objeto Payment
        } catch (Exception e) {
            // Manejo de errores existente
            throw new IllegalArgumentException("Error creating payment: " + e.getMessage());
        }
    }
}
