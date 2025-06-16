package org.lorem.iamservice.domain.services;

import org.lorem.iamservice.domain.model.queries.GetRoleByNameQuery;
import org.lorem.iamservice.domain.model.entities.Role;
import org.lorem.iamservice.domain.model.queries.GetAllRolesQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
