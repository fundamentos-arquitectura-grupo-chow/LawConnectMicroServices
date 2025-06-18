package org.lorem.consultationservice.application.internal.commandservices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.lorem.consultationservice.application.internal.outboundServices.*;
import org.lorem.consultationservice.domain.model.aggregates.Consultation;
import org.lorem.consultationservice.domain.model.commands.*;
import org.lorem.consultationservice.domain.services.ConsultationCommandService;
import org.lorem.consultationservice.infrastructure.persistence.jpa.repositories.ConsultationRepository;

@Service
public class ConsultationCommandServiceImpl implements ConsultationCommandService {
    private final ConsultationRepository consultationRepository;
    private final ExternalPaymentConsultationServices externalPaymentConsultationServices;
    private final ExternalProfileConsultationService externalProfileConsultationService;
    private final ExternalCommunicationConsultationService externalCommunicationConsultationService;
    private final ExternalLegalCaseConsultationService externalLegalCaseConsultationService;
    private final ExternalFollowUpConsultationService externalFollowUpConsultationService;

    public ConsultationCommandServiceImpl(
            ConsultationRepository consultationRepository,
            @Lazy ExternalPaymentConsultationServices externalPaymentConsultationServices1,
            @Lazy ExternalProfileConsultationService externalProfileConsultationService1,
            @Lazy ExternalCommunicationConsultationService externalCommunicationConsultationService,
            @Lazy ExternalLegalCaseConsultationService externalLegalCaseConsultationService,
            @Lazy ExternalFollowUpConsultationService externalFollowUpConsultationService
    ) {
        this.consultationRepository = consultationRepository;
        this.externalPaymentConsultationServices = externalPaymentConsultationServices1;
        this.externalProfileConsultationService = externalProfileConsultationService1;
        this.externalCommunicationConsultationService = externalCommunicationConsultationService;
        this.externalLegalCaseConsultationService = externalLegalCaseConsultationService;
        this.externalFollowUpConsultationService = externalFollowUpConsultationService;
    }

    @Override
    public Long handle(CreateConsultationCommand command) {

        var consultation = new Consultation(command);

        try {
            consultationRepository.save(consultation);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving consultation: " + e.getMessage());
        }

        externalCommunicationConsultationService.createChatRoom(consultation.getId());

        externalLegalCaseConsultationService.createLegalCase(command.title(),command.description(), consultation.getId());

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
    public void handle(ChangeConsultationStatusCommand command) {
        var consultation = consultationRepository.findById(command.id());
        if (consultation.isEmpty()) {
            throw new IllegalArgumentException("Consultation does not exist");
        }
        try {
            externalFollowUpConsultationService.createNotification(
                    "Pago Aceptado",
                    "Ahora la consulta se encuentra pagada",
                    consultation.get().getClientId(),
                    command.id()
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
            var lawyer = externalProfileConsultationService.getLawyerById(consultation.get().getLawyerId());
            externalFollowUpConsultationService.createNotification(
                    "Consulta Aceptada con " + lawyer.get().getProfile().getName().getFullName(),
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
            externalFollowUpConsultationService.createNotification(
                    "Consulta Rechazada",
                    "La consulta ha sido rechazada",
                    consultation.get().getClientId(),
                    command.consultationId()
            );
            consultation.get().setApplicationDenied();

            externalCommunicationConsultationService.deleteChatRoom(consultation.get().getId());
            externalLegalCaseConsultationService.deleteLegalCaseById(consultation.get().getId());
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
            // Crear el pago - la relación se mantiene a través del consultationId
            // que ya se establece al crear el payment
            externalPaymentConsultationServices.createPayment(
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
