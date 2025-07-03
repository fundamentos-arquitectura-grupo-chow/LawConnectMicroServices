package org.lorem.feeingservice.application.internal.eventhandlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.lorem.feeingservice.application.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feeingservice.domain.model.events.PaymentCompletedEvent;

@Service
public class PaymentCompletedEventHandler {

    private final ExternalConsultationPaymentService externalConsultationPaymentService;

    public PaymentCompletedEventHandler(ExternalConsultationPaymentService externalConsultationPaymentService) {
        this.externalConsultationPaymentService = externalConsultationPaymentService;
    }

    @EventListener(PaymentCompletedEvent.class)
    public void onPaymentCompleted(PaymentCompletedEvent event) {
        var consultationId = externalConsultationPaymentService.getConsultationByPaymentId(event.getPaymentId());
        externalConsultationPaymentService.changeConsultationStatus(consultationId.get().getId());
    }
}
