package org.lorem.communicationservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.communicationservice.domain.model.aggregates.Appointment;
import upc.LoremIpsum.lawconnectplatform.consultation.domain.model.aggregates.Consultation;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findAllByConsultation(Consultation consultation);
}
