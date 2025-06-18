package org.lorem.feedinngservice.internal.queryServices;

import org.springframework.stereotype.Service;
import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feedinngservice.internal.outboundServices.ExternalConsultationPaymentService;
import org.lorem.feedinngservice.domain.model.aggregates.Payment;
import org.lorem.feedinngservice.domain.model.queries.GetAllPaymentByClientIdQuery;
import org.lorem.feedinngservice.domain.model.queries.GetPaymentByIdQuery;
import org.lorem.feedinngservice.domain.services.PaymentQueryService;
import org.lorem.feedinngservice.repositories.PaymentRepository;

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

    @Override
    public List<Payment> handle(GetAllPaymentsByConsultationIdQuery query) {
        boolean exists = externalConsultationPaymentService.existsConsultationById(query.consultationId());
        if (!exists) {
            return List.of();
        }
        return paymentRepository.findAllByConsultationId(query.consultationId());
    }

    @Override
    public List<Payment> handle(GetAllPaymentByClientIdQuery query) {
        return paymentRepository.findAllByClientId(query.clientId());
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.paymentId());
    }
}
