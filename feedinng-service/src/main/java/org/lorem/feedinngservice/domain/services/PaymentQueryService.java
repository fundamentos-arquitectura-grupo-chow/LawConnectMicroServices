package org.lorem.feedinngservice.domain.services;

import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feedinngservice.domain.model.aggregates.Payment;
import org.lorem.feedinngservice.domain.model.queries.GetAllPaymentByClientIdQuery;
import org.lorem.feedinngservice.domain.model.queries.GetPaymentByIdQuery;

import java.util.List;
import java.util.Optional;

public interface PaymentQueryService {
    List<Payment> handle(GetAllPaymentsByConsultationIdQuery query);
    List<Payment> handle(GetAllPaymentByClientIdQuery query);
    Optional<Payment> handle(GetPaymentByIdQuery query);
}
