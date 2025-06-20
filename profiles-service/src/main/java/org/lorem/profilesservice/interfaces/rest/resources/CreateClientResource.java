package org.lorem.profilesservice.interfaces.rest.resources;

public record CreateClientResource(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String dni,
        String image_url
) {
}
