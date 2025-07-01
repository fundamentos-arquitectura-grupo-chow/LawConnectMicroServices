package org.lorem.communicationservice.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import upc.LoremIpsum.lawconnectplatform.feeing.interfaces.acl.PaymentContextFacade;

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
