package org.lorem.iamservice.application.internal.commandservices;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.lorem.iamservice.domain.model.valueobjects.Roles;
import org.lorem.iamservice.infrastructure.grpc.ProfileGrpcClient;
import org.springframework.stereotype.Service;
//import org.lorem.iamservice.application.internal.outboundservices.ExternalProfileIAMService;
import org.lorem.iamservice.application.internal.outboundservices.hashing.HashingService;
import org.lorem.iamservice.application.internal.outboundservices.tokens.TokenService;
import org.lorem.iamservice.domain.model.aggregates.User;
import org.lorem.iamservice.domain.model.commands.SignInCommand;
import org.lorem.iamservice.domain.model.commands.SignUpCommand;
//import org.lorem.iamservice.domain.model.valueobjects.Roles;
import org.lorem.iamservice.domain.services.UserCommandService;
import org.lorem.iamservice.infrastructure.persistence.jpa.repositories.UserRepository;
import org.lorem.iamservice.infrastructure.persistence.jpa.repositories.RoleRepository;
import profile.ClientRequest;
import profile.LawyerRequest;

import java.util.Optional;

/**
 * User command service implementation
 * <p>
 *     This class implements the {@link UserCommandService} interface and provides the implementation for the
 *     {@link SignInCommand} and {@link SignUpCommand} commands.
 * </p>
 */
@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final ProfileGrpcClient profileGrpcClient;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(
            UserRepository userRepository,
            HashingService hashingService,
            TokenService tokenService,
            ProfileGrpcClient profileGrpcClient,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.profileGrpcClient = profileGrpcClient;
        this.roleRepository = roleRepository;
    }

    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("User not found");

        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");


        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handle the sign-up command
     * <p>
     *     This method handles the {@link SignUpCommand} command and returns the user.
     * </p>
     * @param command the sign-up command containing the username and password
     * @return the created user
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.email()))
            throw new RuntimeException("Username already exists");

        var roles = command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role name not found")))
                .toList();

        var user = new User(
                command.email(),
                hashingService.encode(command.password()),
                roles
        );

        try {
            if (roles.stream().anyMatch(role -> role.getName() == Roles.LAWYER)) {
                System.out.println("Creating lawyer via gRPC");
                profileGrpcClient.createLawyer(LawyerRequest.newBuilder()
                        .setFirstName(command.firstName())
                        .setLastName(command.lastName())
                        .setEmail(command.email())
                        .setPhoneNumber(command.phoneNumber())
                        .setAddress(command.address())
                        .setDni(command.dni())
                        .setImageUrl(command.image_url())
                        .build());
            } else if (roles.stream().anyMatch(role -> role.getName() == Roles.CLIENT)) {
                System.out.println("Creating client via gRPC");
                profileGrpcClient.createClient(ClientRequest.newBuilder()
                        .setFirstName(command.firstName())
                        .setLastName(command.lastName())
                        .setEmail(command.email())
                        .setPhoneNumber(command.phoneNumber())
                        .setAddress(command.address())
                        .setDni(command.dni())
                        .setImageUrl(command.image_url())
                        .build());
            } else {
                throw new RuntimeException("Role not found");
            }
        } catch (io.grpc.StatusRuntimeException e) {
            System.err.println("gRPC error: " + e.getStatus().getCode() + " - " + e.getStatus().getDescription());
            throw new RuntimeException("Failed to create user profile via gRPC: " + e.getMessage(), e);
        }

        userRepository.save(user);
        return userRepository.findByUsername(command.email());
    }
}
