package org.lorem.profilesservice.interfaces.rest.transform;

import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.interfaces.rest.resources.ProfileResource;

public class ProfileResourceFromEntityAssembler {
    public static ProfileResource ToResourceFromEntity(Profile entity) {
        return new ProfileResource(
                entity.getId(),
                entity.getName().firstName(),
                entity.getName().lastName(),
                entity.getEmail().address(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getDNI(),
                entity.getImage_url()
        );
    }
}
