package org.lorem.profilesservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.domain.model.queries.GetAllProfilesQuery;
import org.lorem.profilesservice.domain.model.queries.GetProfileByIdQuery;
import org.lorem.profilesservice.domain.services.ProfileQueryService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileQueryServiceImpl implements ProfileQueryService {

    private final ProfileRepository profileRepository;

    public ProfileQueryServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Profile> handle(GetAllProfilesQuery query) {
        return profileRepository.findAll();
    }

    @Override
    public Optional<Profile> handle(GetProfileByIdQuery query) {
        return profileRepository.findById(query.profileId());
    }
}
