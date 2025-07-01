package org.lorem.feeingservice.domain.services;


import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentByClientIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetPaymentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    List<Payment> handle(GetAllPaymentsByConsultationIdQuery query);
    List<Payment> handle(GetAllPaymentByClientIdQuery query);
    Optional<Payment> handle(GetPaymentByIdQuery query);
}
