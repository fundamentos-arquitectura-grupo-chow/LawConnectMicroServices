package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.aggregates.Lawyer;
import org.lorem.profilesservice.domain.model.valueobjects.LawyerType;
import org.lorem.profilesservice.interfaces.rest.resources.LawyerResource;

import java.util.Set;
import java.util.stream.Collectors;

public class LawyerResourceFromEntityAssembler {
    public static LawyerResource ToEntityFromResource(Lawyer entity) {
        Set<String> lawyerTypeStrings = entity.getLawyerTypes().stream()
                .map(LawyerType::name)
                .collect(Collectors.toSet());


        return new LawyerResource(
                entity.getId(),
                entity.getProfile(),
                lawyerTypeStrings,
                entity.getPrices()
        );
    }
}
