package org.lorem.iamservice.interfaces.rest.transform;

import org.lorem.iamservice.domain.model.aggregates.User;
import org.lorem.iamservice.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(
                user.getId(),
                user.getUsername(),
                token,
                user.getRoles().toString()
        );
    }
}
