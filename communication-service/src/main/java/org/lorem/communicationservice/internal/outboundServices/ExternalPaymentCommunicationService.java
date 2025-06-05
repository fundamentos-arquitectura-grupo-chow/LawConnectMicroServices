package org.lorem.communicationservice.application.internal.outboundServices;

import org.lorem.feeing.domain.model.aggregates.Payment;
import org.lorem.feeing.interfaces.acl.PaymentContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalPaymentCommunicationService {

    private final PaymentContextFacade paymentContextFacade;

    public ExternalPaymentCommunicationService(PaymentContextFacade paymentContextFacade) {
        this.paymentContextFacade = paymentContextFacade;
    }

    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentContextFacade.getPaymentById(paymentId);
    }
}
