package org.lorem.iamservice.interfaces.rest.transform;

import org.lorem.iamservice.domain.model.commands.SignInCommand;
import org.lorem.iamservice.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}
