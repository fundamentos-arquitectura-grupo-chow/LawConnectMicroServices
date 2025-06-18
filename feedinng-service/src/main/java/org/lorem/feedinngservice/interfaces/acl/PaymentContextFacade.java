package org.lorem.feedinngservice.interfaces.acl;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.lorem.feedinngservice.domain.model.aggregates.Payment;
import org.lorem.feedinngservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feedinngservice.domain.model.commands.DeletePaymentCommand;
import org.lorem.feedinngservice.domain.model.queries.GetPaymentByIdQuery;
import org.lorem.feedinngservice.domain.services.PaymentCommandService;
import org.lorem.feedinngservice.domain.services.PaymentQueryService;
import org.lorem.feedinngservice.interfaces.rest.resources.PaymentResource;
import org.lorem.feedinngservice.interfaces.rest.transform.PaymentResourceFromEntityAssembler;

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

    public List<Payment> getPaymentsByConsultationId(Long consultationId){
        return paymentQueryService.handle(new GetAllPaymentsByConsultationIdQuery(consultationId));
    }

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