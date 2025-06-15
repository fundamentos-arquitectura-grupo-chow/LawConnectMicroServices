package org.lorem.iamservice.domain.services;

import org.lorem.iamservice.domain.model.commands.SignInCommand;

import java.util.Optional;

public interface UserCommandService {

    Optional<String> handle(SignInCommand command);
}
