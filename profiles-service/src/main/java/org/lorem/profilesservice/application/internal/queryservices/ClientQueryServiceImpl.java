package org.lorem.profilesservice.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.lorem.profilesservice.domain.model.aggregates.Client;
import org.lorem.profilesservice.domain.model.queries.GetAllClientsQuery;
import org.lorem.profilesservice.domain.model.queries.GetClientByIdQuery;
import org.lorem.profilesservice.domain.model.queries.GetClientIdByEmailQuery;
import org.lorem.profilesservice.domain.model.valueobjects.EmailAddress;
import org.lorem.profilesservice.domain.services.ClientQueryService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientQueryServiceImpl implements ClientQueryService {

    private final ClientRepository clientRepository;
    private final ProfileRepository profileRepository;

    public ClientQueryServiceImpl(ClientRepository clientRepository, ProfileRepository profileRepository) {
        this.clientRepository = clientRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<Client> handle(GetAllClientsQuery query) {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> handle(GetClientByIdQuery query) {
        return clientRepository.findById(query.clientId());
    }

    @Override
    public Optional<Long> handle(GetClientIdByEmailQuery query) {
        var profile = profileRepository.findByEmail(new EmailAddress(query.email()));
        var client = clientRepository.findByProfile(profile.get());
        return Optional.of(client.get().getId());
    }
}
