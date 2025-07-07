package org.lorem.feeingservice.application.internal.commandServices;

import io.grpc.StatusRuntimeException;
import org.lorem.feeingservice.infrastructure.grpc.ConsultationGrpcClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.lorem.feeingservice.application.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feeingservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feeingservice.domain.model.commands.DeletePaymentCommand;
import org.lorem.feeingservice.domain.model.valueObjects.PaymentStatus;
import org.lorem.feeingservice.domain.services.PaymentCommandService;
import org.lorem.feeingservice.infrastructure.repositories.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final ExternalConsultationPaymentService externalConsultationPaymentService;
    private final ConsultationGrpcClient consultationGrpcClient;

    public PaymentCommandServiceImpl(
            PaymentRepository paymentRepository,
            @Lazy ExternalConsultationPaymentService externalConsultationPaymentService1, ConsultationGrpcClient consultationGrpcClient) {
        this.paymentRepository = paymentRepository;
        this.externalConsultationPaymentService = externalConsultationPaymentService1;
        this.consultationGrpcClient = consultationGrpcClient;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        try {
            //var consultationOptional = externalConsultationPaymentService.getConsultationId(command.consultationId());
            var consultationId = consultationGrpcClient.getConsultationId(command.consultationId());
            if (consultationId == 0) {
                throw new IllegalArgumentException("La consulta con ID " + command.consultationId() + " no existe");
            }
            //ConsultationDto consultationDto = ConsultationDto.fromConsultation(consultationOptional.get());
            var payment = new Payment(command, consultationId);
            System.out.println("Payment created");
            paymentRepository.save(payment);
            return Optional.of(payment);
        } catch (io.grpc.StatusRuntimeException e) {
            // Manejo específico para errores gRPC
            switch (e.getStatus().getCode()) {
                case NOT_FOUND:
                    // La consulta no se encontró
                    throw new IllegalArgumentException("Consulta no encontrada: " + command.consultationId(), e);
                case UNAVAILABLE:
                    // El servicio de consultas no está disponible
                    throw new RuntimeException("Servicio de consultas no disponible. Intente más tarde", e);
                default:
                    // Otros errores gRPC
                    throw new RuntimeException("Error en la comunicación con el servicio de consultas: " +
                            e.getStatus().getDescription(), e);
            }
        } catch (Exception e) {
            // Para otros errores no relacionados con gRPC
            throw new RuntimeException("Error al crear el pago: " + e.getMessage(), e);
        }

    }

    @Override
    public Optional<Payment> handle(CompletePaymentCommand command) {

        if (command.cardNumber().length()!=16 || command.cvv().length()!=3){
            return Optional.empty();
        }

        var payment = paymentRepository.findById(command.paymentId());
        if (payment.isEmpty()){
            return Optional.empty();
        }
        payment.get().updateCard(command);
        System.out.println("Payment updated");
        payment.get().setStatus(PaymentStatus.COMPLETADO);
        System.out.println("Payment updated");

        payment.get().finishProject();

        paymentRepository.save(payment.get());
        return payment;
    }

    @Override
    public void handle(DeletePaymentCommand command) {
        var payment = paymentRepository.findById(command.PaymentId());
        if (payment.isEmpty()) {
            throw new IllegalArgumentException("Payment not found");
        }
        paymentRepository.deleteById(command.PaymentId());
    }
}
