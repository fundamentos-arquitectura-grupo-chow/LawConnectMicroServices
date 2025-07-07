package org.lorem.consultationservice.infrastructure.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class FeeingKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public FeeingKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentCreatedMessage(
            Long consultationId,
            Long clientId,
            Double amount,
            Integer currency
    ) {
        String message = String.format("PAYMENT_CREATED:%d:%d:%d:%d", consultationId, clientId, amount, currency);
        kafkaTemplate.send("feeing-topic", message);
    }

    public void sendPaymentCompletedMessage(Long paymentId, String cardNumber, LocalDate expiryDate, String cvv) {
        String message = String.format("PAYMENT_COMPLETED:%d:%s:%s:%s",
                paymentId,
                cardNumber,
                expiryDate.toString(),
                cvv
        );
        kafkaTemplate.send("feeing-topic", message);
    }
}