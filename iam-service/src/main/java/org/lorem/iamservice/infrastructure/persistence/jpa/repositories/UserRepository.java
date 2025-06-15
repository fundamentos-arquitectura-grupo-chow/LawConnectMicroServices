package org.lorem.iamservice.infrastructure.persistence.jpa.repositories;

import org.lorem.iamservice.domain.model.aggregate.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    Optional<User> findByUsername(String username);
}
