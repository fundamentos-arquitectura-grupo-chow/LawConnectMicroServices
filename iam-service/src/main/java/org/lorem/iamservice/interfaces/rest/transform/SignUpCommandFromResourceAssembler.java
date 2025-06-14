package org.lorem.iamservice.interfaces.rest.transform;

import org.lorem.iamservice.domain.model.commands.SignUpCommand;
import org.lorem.iamservice.domain.model.entities.Role;
import org.lorem.iamservice.interfaces.rest.resources.SignUpResource;

import java.util.ArrayList;

public class SignUpCommandFromResourceAssembler {
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        var roles = resource.roles() != null ? resource.roles().stream().map(name -> Role.toRoleFromName(name)).toList() : new ArrayList<Role>();
        return new SignUpCommand(
                resource.email(),
                resource.password(),
                roles,
                resource.firstName(),
                resource.lastName(),
                resource.phoneNumber(),
                resource.address(),
                resource.dni(),
                resource.image_url()
        );
    }
}
