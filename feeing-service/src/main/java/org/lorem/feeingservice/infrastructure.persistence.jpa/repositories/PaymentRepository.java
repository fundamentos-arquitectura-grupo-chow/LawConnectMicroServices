package org.lorem.feeingservice.infrastructure.persistence.jpa.repositories;


import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByClientId(Long clientId);
    List<Payment> findAllByConsultationId(Long consultationId);
}
