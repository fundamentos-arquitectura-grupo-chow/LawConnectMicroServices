package org.lorem.feedinngservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.feedinngservice.domain.model.aggregates.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByClientId(Long clientId);
    List<Payment> findAllByConsultationId(Long consultationId);
}
