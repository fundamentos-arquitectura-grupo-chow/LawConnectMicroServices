package org.lorem.feeingservice.application.internal.queryServices;

import org.lorem.feeingservice.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentsQuery;
import org.springframework.stereotype.Service;
import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentByClientIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetPaymentByIdQuery;
import org.lorem.feeingservice.domain.services.PaymentQueryService;
import org.lorem.feeingservice.infrastructure.repositories.PaymentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public List<Payment> handle(GetAllPaymentsByConsultationIdQuery query) {
        return paymentRepository.findAllByConsultation(query.consultationId());
    }

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
