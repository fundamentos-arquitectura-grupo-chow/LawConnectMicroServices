package org.lorem.iamservice.domain.services;

import org.lorem.iamservice.domain.model.aggregates.User;
import org.lorem.iamservice.domain.model.queries.GetAllUsersQuery;
import org.lorem.iamservice.domain.model.queries.GetUserByIdQuery;
import org.lorem.iamservice.domain.model.queries.GetUserByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByUsernameQuery query);

}
