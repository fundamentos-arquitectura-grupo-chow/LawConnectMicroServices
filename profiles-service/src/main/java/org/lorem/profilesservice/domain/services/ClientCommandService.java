package org.lorem.profilesservice.domain.services;

import org.lorem.profilesservice.domain.model.aggregates.Client;
import org.lorem.profilesservice.domain.model.commands.CreateClientCommand;
import org.lorem.profilesservice.domain.model.commands.IncrementConsultationsMadeCommand;
import org.lorem.profilesservice.domain.model.commands.IncrementPaidServicesCommand;

import java.util.Optional;

public interface ClientCommandService {
    Optional<Client> handle(CreateClientCommand command);
    void handle(IncrementPaidServicesCommand command);
    void handle(IncrementConsultationsMadeCommand command);
}
