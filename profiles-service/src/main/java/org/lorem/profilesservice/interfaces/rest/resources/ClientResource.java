package org.lorem.profilesservice.interfaces.rest.resources;

import org.lorem.profilesservice.domain.model.aggregates.Profile;

public record ClientResource(
        Long id,
        Profile profile,
        Integer consultationCount,
        Integer paidServices
) {
}
