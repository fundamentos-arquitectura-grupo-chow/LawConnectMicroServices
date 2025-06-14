package org.lorem.iamservice.interfaces.rest.resources;

public record AuthenticatedUserResource(
        Long id,
        String username,
        String token,
        String role
) {

}
