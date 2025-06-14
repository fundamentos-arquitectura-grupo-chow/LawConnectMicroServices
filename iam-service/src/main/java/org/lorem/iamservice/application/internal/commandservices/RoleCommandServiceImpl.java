package org.lorem.iamservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.iamservice.domain.model.commands.SeedRolesCommand;
import org.lorem.iamservice.domain.model.entities.Role;
import org.lorem.iamservice.domain.model.valueobjects.Roles;
import org.lorem.iamservice.domain.services.RoleCommandService;
import org.lorem.iamservice.infrastructure.persistence.jpa.repositories.RoleRepository;

import java.util.Arrays;

/**
 * Implementation of {@link RoleCommandService} to handle {@link SeedRolesCommand}
 */
@Service
public class RoleCommandServiceImpl implements RoleCommandService {

    private final RoleRepository roleRepository;

    public RoleCommandServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * This method will handle the {@link SeedRolesCommand} and will create the roles if not exists
     * @param command {@link SeedRolesCommand}
     * @see SeedRolesCommand
     */
    @Override
    public void handle(SeedRolesCommand command) {
        Arrays.stream(Roles.values()).forEach(role -> {
            if(!roleRepository.existsByName(role)) {
                roleRepository.save(new Role(Roles.valueOf(role.name())));
            }
        } );
    }
}
