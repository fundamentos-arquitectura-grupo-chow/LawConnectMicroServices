package org.lorem.consultationservice.application.internal.outboundServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.feeing.domain.model.aggregates.Payment;
import upc.LoremIpsum.lawconnectplatform.feeing.interfaces.acl.PaymentContextFacade;
import upc.LoremIpsum.lawconnectplatform.feeing.interfaces.rest.resources.PaymentResource;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalPaymentConsultationServices {

    private final PaymentContextFacade paymentContextFacade;

    public ExternalPaymentConsultationServices(PaymentContextFacade paymentContextFacade) {
        this.paymentContextFacade = paymentContextFacade;
    }

    public Optional<Payment> createPayment(Long consultationId, Long clientId, Double amount, Integer status) {
        return paymentContextFacade.createPayment(consultationId, clientId, amount, status);
    }

    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentContextFacade.getPaymentById(paymentId);
    }

    public void deletePayment(Long paymentId) {
        paymentContextFacade.deletePaymentById(paymentId);
    }

    public List<PaymentResource> createPaymentListResource(List<Payment> payments) {
        return paymentContextFacade.createPaymentListResource(payments);
    }

    public List<Payment> getPaymentsByConsultationId(Long consultationId) {
        return paymentContextFacade.getPaymentsByConsultationId(consultationId);
    }
}