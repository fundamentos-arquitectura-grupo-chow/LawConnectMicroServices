package org.lorem.feedinngservice.internal.eventhandlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.lorem.feedinngservice.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feedinngservice.domain.model.events.PaymentCompletedEvent;
import org.lorem.feedinngservice.repositories.PaymentRepository;

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
            externalConsultationPaymentService.changeConsultationStatus(payment.getConsultationId());
        }
    }
}