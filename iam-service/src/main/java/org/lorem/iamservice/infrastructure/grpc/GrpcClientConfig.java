package org.lorem.iamservice.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import profile.ProfileServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Bean
    public ProfileGrpcClient profileGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("profiles-service", 6565)
                .usePlaintext()
                .build();
        return new ProfileGrpcClient(ProfileServiceGrpc.newBlockingStub(channel));
    }
}