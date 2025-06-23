package org.lorem.profilesservice.application.internal.commandservices;

import org.lorem.profilesservice.infrastructure.grpc.IAMGrpcClient;
import org.springframework.stereotype.Service;
//import org.lorem.profilesservice.application.internal.outboundServices.ExternalIAMProfileService;
import org.lorem.profilesservice.domain.model.aggregates.Lawyer;
import org.lorem.profilesservice.domain.model.aggregates.Profile;
import org.lorem.profilesservice.domain.model.commands.AddLawyerPricesCommand;
import org.lorem.profilesservice.domain.model.commands.AddLawyerTypeCommand;
import org.lorem.profilesservice.domain.model.commands.CreateLawyerCommand;
import org.lorem.profilesservice.domain.model.valueobjects.EmailAddress;
import org.lorem.profilesservice.domain.services.LawyerCommandService;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.LawyerRepository;
import org.lorem.profilesservice.infrastructure.persistence.jpa.repositories.ProfileRepository;

import java.util.Optional;

@Service
public class LawyerCommandServiceImpl implements LawyerCommandService {

    private final LawyerRepository lawyerRepository;
    private final ProfileRepository profileRepository;
    private final IAMGrpcClient iamGrpcClient;

    public LawyerCommandServiceImpl(
            LawyerRepository lawyerRepository,
            ProfileRepository profileRepository, IAMGrpcClient iamGrpcClient
    ) {
        this.lawyerRepository = lawyerRepository;
        this.profileRepository = profileRepository;
        this.iamGrpcClient = iamGrpcClient;
    }

    @Override
    public Optional<Lawyer> handle(CreateLawyerCommand command) {
        var profileId = profileRepository.findByEmail(new EmailAddress(command.email()));
        var profile = new Profile();

        if (profileId.isEmpty()) {
            var userId = iamGrpcClient.getUserIdByUsername(command.email());
            profile = new Profile(command, userId);
        } else {
            throw new IllegalArgumentException("Lawyer already exists");
        }

        var lawyer = new Lawyer(profile);

        profileRepository.save(profile);
        lawyerRepository.save(lawyer);

        return Optional.of(lawyer);
    }

    @Override
    public void handle(AddLawyerPricesCommand command) {
        if (lawyerRepository.findById(command.lawyerId()).isEmpty()) {
            throw new IllegalArgumentException("Lawyer not found");
        }
        var lawyer = lawyerRepository.findById(command.lawyerId()).get();
        lawyer.setPrices(command);

        lawyerRepository.save(lawyer);
    }

    @Override
    public void handle(AddLawyerTypeCommand command) {
        if (lawyerRepository.findById(command.lawyerId()).isEmpty()) {
            throw new IllegalArgumentException("Lawyer not found");
        }
        var lawyer = lawyerRepository.findById(command.lawyerId()).get();
        lawyer.addLawyerType(command);

        lawyerRepository.save(lawyer);
    }
}
