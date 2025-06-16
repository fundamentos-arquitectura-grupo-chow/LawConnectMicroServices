package org.lorem.iamservice.interfaces.rest.transform;

import org.lorem.iamservice.domain.model.aggregates.User;
import org.lorem.iamservice.domain.model.entities.Role;
import org.lorem.iamservice.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        var roles = user.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(user.getId(), user.getUsername(), roles);
    }
}
