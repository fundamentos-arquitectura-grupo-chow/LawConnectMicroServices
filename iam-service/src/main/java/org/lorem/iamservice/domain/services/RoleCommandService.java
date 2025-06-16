package org.lorem.iamservice.domain.services;

import org.lorem.iamservice.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
