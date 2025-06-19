package org.lorem.feeingservice.application.internal.eventhandlers;

import org.lorem.feeingservice.application.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feeingservice.domain.model.events.PaymentCompletedEvent;
import org.lorem.feeingservice.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentCompletedEventHandler {
    private final ExternalConsultationPaymentService externalConsultationPaymentService;
    private final PaymentRepository paymentRepository;

    public PaymentCompletedEventHandler(
            ExternalConsultationPaymentService externalConsultationPaymentService,
            PaymentRepository paymentRepository) {
        this.externalConsultationPaymentService = externalConsultationPaymentService;
        this.paymentRepository = paymentRepository;
    }

    @EventListener(PaymentCompletedEvent.class)
    public void onPaymentCompleted(PaymentCompletedEvent event) {
        var payment = paymentRepository.findById(event.getPaymentId()).orElse(null);
        if (payment != null) {
//            externalConsultationPaymentService.changeConsultationStatus(payment.getConsultationId());
        }
    }
}