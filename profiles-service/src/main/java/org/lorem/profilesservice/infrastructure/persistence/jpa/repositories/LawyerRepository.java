package org.lorem.profilesservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.profilesservice.domain.model.aggregates.Lawyer;
import org.lorem.profilesservice.domain.model.aggregates.Profile;

import java.util.Optional;

@Repository
public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    Optional<Lawyer> findByProfile(Profile profile);
}
