package org.lorem.feeingservice.infrastructure.kafka;

import org.lorem.feeingservice.interfaces.acl.PaymentContextFacade;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeeingKafkaConsumer {

    private final PaymentContextFacade paymentContextFacade;

    public FeeingKafkaConsumer(PaymentContextFacade paymentContextFacade) {
        this.paymentContextFacade = paymentContextFacade;
    }

    @KafkaListener(topics = "feeing-topic", groupId = "feeing-group")
    public void consumeMessage(String message) {
        if (message.startsWith("PAYMENT_COMPLETED:")) {
            String[] parts = message.split(":");
            Long paymentId = Long.parseLong(parts[1]);
            String cardNumber = parts[2];
            LocalDate expiryDate = LocalDate.parse(parts[3]);
            String cvv = parts[4];
            handlePaymentCompleted(paymentId, cardNumber, expiryDate, cvv);
        }
    }

    private void handlePaymentCompleted(Long paymentId, String cardNumber, LocalDate expiryDate, String cvv) {
        System.out.println("Handling payment completion for ID: " + paymentId);
        paymentContextFacade.CompletePayment(paymentId, cardNumber, expiryDate, cvv);
    }
}