package org.lorem.profilesservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.domain.model.commands.CreateProfileCommand;
import org.lorem.profilesservice.domain.model.valueobjects.EmailAddress;
import org.lorem.profilesservice.domain.services.ProfileCommandService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public Optional<Profile> handle(CreateProfileCommand command) {
        var emailAddress = new EmailAddress(command.email());
        if (profileRepository.existsByEmail(emailAddress))
            throw new IllegalArgumentException(
                    "Profile with email " + command.email() + " already exists");
        var profile = new Profile(command);
        profileRepository.save(profile);
        return Optional.of(profile);
    }
}
