package org.lorem.feedinngservice.internal.commandServices;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.lorem.feedinngservice.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feedinngservice.domain.model.aggregates.Payment;
import org.lorem.feedinngservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feedinngservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feedinngservice.domain.model.commands.DeletePaymentCommand;
import org.lorem.feedinngservice.domain.model.valueObjects.PaymentStatus;
import org.lorem.feedinngservice.domain.services.PaymentCommandService;
import org.lorem.feedinngservice.repositories.PaymentRepository;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {

    private final PaymentRepository paymentRepository;
    private final ExternalConsultationPaymentService externalConsultationPaymentService;

    public PaymentCommandServiceImpl(
            PaymentRepository paymentRepository,
            @Lazy ExternalConsultationPaymentService externalConsultationPaymentService1) {
        this.paymentRepository = paymentRepository;
        this.externalConsultationPaymentService = externalConsultationPaymentService1;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        boolean exists = externalConsultationPaymentService.existsConsultationById(command.consultationId());
        if (!exists) {
            return Optional.empty();
        }
        var payment = new Payment(command);
        System.out.println("Payment created");
        paymentRepository.save(payment);
        return Optional.of(payment);
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
