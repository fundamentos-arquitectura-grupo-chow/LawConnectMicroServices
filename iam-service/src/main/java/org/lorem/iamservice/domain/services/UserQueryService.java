package org.lorem.iamservice.domain.services;

import org.lorem.iamservice.domain.model.aggregate.User;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    /**
     * Handle get all users query.
     *
     * @param query The {@link GetAllUsersQuery} query.
     * @return The list of users.
     */
    List<User> handle(GetAllUsersQuery query);

    /**
     * Handle get user by id query.
     *
     * @param query The {@link GetUserByIdQuery} query containing the user id.
     * @return The user found.
     */
    Optional<User> handle(GetUserByIdQuery query);

    /**
     * Handle get user by username query.
     *
     * @param query The {@link GetUserByUsernameQuery} query containing the username.
     * @return The user found.
     */
    Optional<User> handle(GetUserByUsernameQuery query);
}
