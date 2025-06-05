package org.lorem.communicationservice.infrastructure.persistence.jpa.repositories;

import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import org.lorem.consultation.domain.model.aggregates.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByConsultation(Consultation consultation);
}
