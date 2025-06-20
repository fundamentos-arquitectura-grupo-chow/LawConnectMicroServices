package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Client;
import org.lorem.profilesservice.domain.model.queries.GetAllClientsQuery;
import org.lorem.profilesservice.domain.model.queries.GetClientByIdQuery;
import org.lorem.profilesservice.domain.model.queries.GetClientIdByEmailQuery;

import java.util.List;
import java.util.Optional;

public interface ClientQueryService {
    List<Client> handle(GetAllClientsQuery query);
    Optional<Client> handle(GetClientByIdQuery query);
    Optional<Long> handle(GetClientIdByEmailQuery query);
}
