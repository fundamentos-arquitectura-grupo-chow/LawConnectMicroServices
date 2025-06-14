package org.lorem.profilesservice.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.lorem.profilesservice.application.internal.outboundServices.ExternalIAMProfileService;
import org.lorem.profilesservice.domain.model.aggregates.Client;
import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.domain.model.commands.CreateClientCommand;
import org.lorem.profilesservice.domain.model.commands.IncrementConsultationsMadeCommand;
import org.lorem.profilesservice.domain.model.commands.IncrementPaidServicesCommand;
import org.lorem.profilesservice.domain.model.valueobjects.EmailAddress;
import org.lorem.profilesservice.domain.services.ClientCommandService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {

    private final ProfileRepository profileRepository;
    private final ClientRepository clientRepository;
    private final ExternalIAMProfileService externalIAMProfileService;

    public ClientCommandServiceImpl(ProfileRepository profileRepository, ClientRepository clientRepository, ExternalIAMProfileService externalIAMProfileService) {
        this.profileRepository = profileRepository;
        this.clientRepository = clientRepository;
        this.externalIAMProfileService = externalIAMProfileService;
    }


    @Override
    public Optional<Client> handle(CreateClientCommand command) {

        var profileId = profileRepository.findByEmail(new EmailAddress(command.email()));
        var profile = new Profile();

        if (profileId.isEmpty()) {
            var userId = externalIAMProfileService.getUserIdByUsername(command.email());
            profile = new Profile(command, userId);
        } else {
            throw new IllegalArgumentException("Client already exists");
        }

        var client = new Client(profile);

        profileRepository.save(profile);
        clientRepository.save(client);

        return Optional.of(client);
    }

    @Override
    public void handle(IncrementPaidServicesCommand command) {
        if(clientRepository.findById(command.clientId()).isPresent()){
            var client = clientRepository.findById(command.clientId()).get();
            client.incrementPaidServices();
            clientRepository.save(client);
        }
    }

    @Override
    public void handle(IncrementConsultationsMadeCommand command) {
        if(clientRepository.findById(command.clientId()).isPresent()){
            var client = clientRepository.findById(command.clientId()).get();
            client.incrementConsultationCount();
            clientRepository.save(client);
        }
    }
}
