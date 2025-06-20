package org.lorem.profilesservice.interfaces.rest.resources;

import org.lorem.profilesservice.domain.model.aggregates.Profile;

import java.util.Set;

public record LawyerResource(
        Long id,
        Profile profile,
        Set<String> lawyerTypes,
        Double Prices
) {
}
