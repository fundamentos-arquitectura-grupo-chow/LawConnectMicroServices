package org.lorem.iamservice.interfaces.rest.transform;

import org.lorem.iamservice.domain.model.entities.Role;
import org.lorem.iamservice.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}
