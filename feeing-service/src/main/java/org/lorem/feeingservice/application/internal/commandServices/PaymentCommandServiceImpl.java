package org.lorem.feeingservice.application.internal.commandServices;

import org.springframework.stereotype.Service;
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

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
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
