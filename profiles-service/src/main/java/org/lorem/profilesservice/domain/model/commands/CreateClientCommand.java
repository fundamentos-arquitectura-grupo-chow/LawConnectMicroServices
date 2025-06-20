package org.lorem.profilesservice.domain.model.commands;

public record CreateClientCommand(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String address,
        String dni,
        String image_url
) {
}
