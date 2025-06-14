package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.domain.model.commands.CreateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
}
