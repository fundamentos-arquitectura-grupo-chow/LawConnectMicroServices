package org.lorem.iamservice.domain.model.commands;

import org.lorem.iamservice.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(
        String email,
        String password,
        List<Role> roles,
        String firstName,
        String lastName,
        String phoneNumber,
        String address,
        String dni,
        String image_url
) {
}