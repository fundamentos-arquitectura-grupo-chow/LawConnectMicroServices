package org.lorem.profilesservice.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.domain.model.valueobjects.EmailAddress;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
    boolean existsByEmail(EmailAddress email);
    Optional<Profile> findByEmail(EmailAddress email);
}
