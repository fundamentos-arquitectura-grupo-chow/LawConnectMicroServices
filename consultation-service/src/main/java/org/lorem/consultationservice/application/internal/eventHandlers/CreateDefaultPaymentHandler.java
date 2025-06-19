package org.lorem.consultationservice.application.internal.eventHandlers;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
//import org.lorem.consultationservice.application.internal.outboundServices.ExternalPaymentConsultationServices;
import org.lorem.consultationservice.domain.model.events.CreateDefaultPaymentEvent;

@Service
public class CreateDefaultPaymentHandler {

    /*private final ExternalPaymentConsultationServices externalPaymentConsultationServices;

    public CreateDefaultPaymentHandler(ExternalPaymentConsultationServices externalPaymentConsultationServices) {
        this.externalPaymentConsultationServices = externalPaymentConsultationServices;
    }

    @EventListener(CreateDefaultPaymentEvent.class)
    public void on(CreateDefaultPaymentEvent event) {
        externalPaymentConsultationServices.createPayment(event.getConsultationId() ,event.getClientId(), event.getAmount(), event.getCurrency());
    }*/
}
