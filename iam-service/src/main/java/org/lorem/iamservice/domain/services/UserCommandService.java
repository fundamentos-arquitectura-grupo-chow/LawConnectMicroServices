package org.lorem.iamservice.domain.services;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.lorem.iamservice.domain.model.aggregates.User;
import org.lorem.iamservice.domain.model.commands.SignInCommand;
import org.lorem.iamservice.domain.model.commands.SignUpCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<ImmutablePair<User, String>> handle(SignInCommand command);
    Optional<User> handle(SignUpCommand command);


}
