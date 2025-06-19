package org.lorem.feeingservice.interfaces.acl;

//import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feeingservice.domain.model.commands.DeletePaymentCommand;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentByConsultationIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetPaymentByIdQuery;
import org.lorem.feeingservice.domain.services.PaymentCommandService;
import org.lorem.feeingservice.domain.services.PaymentQueryService;
import org.lorem.feeingservice.interfaces.rest.resources.PaymentResource;
import org.lorem.feeingservice.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentContextFacade {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentContextFacade(
            @Lazy PaymentCommandService paymentCommandService,
            @Lazy PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    public Optional<Payment> createPayment(
            Long consultationId,
            Long clientId,
            Double amount,
            Integer currency
    ){
        return paymentCommandService.handle(new CreatePaymentCommand(consultationId, clientId, amount, currency));
    }

    public Optional<Payment> getPaymentById(Long paymentId){
        return paymentQueryService.handle(new GetPaymentByIdQuery(paymentId));
    }

/*
    public List<Payment> getPaymentsByConsultationId(Long consultationId){
        return paymentQueryService.handle(new GetAllPaymentsByConsultationIdQuery(consultationId));
    }
*/

    public void deletePaymentById(Long paymentId){
        paymentCommandService.handle(new DeletePaymentCommand(paymentId));
    }

    public List<PaymentResource> createPaymentListResource(
            List<Payment> payment
    ){
        var paymentsResources = payment.stream()
                .map(PaymentResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return Optional.of(paymentsResources).orElseThrow();
    }
}