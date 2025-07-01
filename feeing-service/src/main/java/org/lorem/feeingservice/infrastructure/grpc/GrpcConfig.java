package org.lorem.feeingservice.infrastructure.grpc;

import consultation.ConsultationServiceGrpc;
import profile.ProfileServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public ConsultationGrpcClient consultationGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("consultation-service", 6565)
                .usePlaintext()
                .build();

        return new ConsultationGrpcClient(
                ConsultationServiceGrpc.newBlockingStub(channel)
        );
    }

    @Bean
    public ProfileGrpcClient profileGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("profiles-service", 6565)
                .usePlaintext()
                .build();

        return new ProfileGrpcClient(
                ProfileServiceGrpc.newBlockingStub(channel)
        );
    }
}