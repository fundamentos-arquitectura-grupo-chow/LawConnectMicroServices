package org.lorem.feeingservice.application.internal.queryServices;

//import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feeingservice.application.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentByClientIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentsQuery;
import org.lorem.feeingservice.domain.model.queries.GetPaymentByIdQuery;
import org.lorem.feeingservice.domain.services.PaymentQueryService;
import org.lorem.feeingservice.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;
    private final ExternalConsultationPaymentService externalConsultationPaymentService;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository, ExternalConsultationPaymentService externalConsultationPaymentService) {
        this.paymentRepository = paymentRepository;
        this.externalConsultationPaymentService = externalConsultationPaymentService;
    }

    /*@Override
    public List<Payment> handle(GetAllPaymentsByConsultationIdQuery query) {
        boolean exists = externalConsultationPaymentService.existsConsultationById(query.consultationId());
        if (!exists) {
            return List.of();
        }
        return paymentRepository.findAllByConsultationId(query.consultationId());
    }*/

    @Override
    public List<Payment> handle(GetAllPaymentByClientIdQuery query) {
        return paymentRepository.findAllByClientId(query.clientId());
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.paymentId());
    }

    @Override
    public List<Payment> handle(GetAllPaymentsQuery query) {
        return paymentRepository.findAll();
    }
}
