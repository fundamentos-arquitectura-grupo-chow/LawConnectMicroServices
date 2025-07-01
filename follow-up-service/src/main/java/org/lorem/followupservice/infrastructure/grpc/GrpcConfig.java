package org.lorem.followupservice.infrastructure.grpc;

import consultation.ConsultationServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import profile.ProfileServiceGrpc;

@Configuration
public class GrpcConfig {

    @Bean
    public ManagedChannel consultationChannel() {
        return ManagedChannelBuilder
                .forAddress("consultation-service", 6565)
                .usePlaintext()
                .build();
    }

    @Bean
    public ConsultationServiceGrpc.ConsultationServiceBlockingStub consultationServiceBlockingStub(ManagedChannel consultationChannel) {
        return ConsultationServiceGrpc.newBlockingStub(consultationChannel);
    }

    @Bean
    public ConsultationGrpcClient consultationGrpcClient(
            ConsultationServiceGrpc.ConsultationServiceBlockingStub consultationServiceBlockingStub) {
        return new ConsultationGrpcClient(consultationServiceBlockingStub);
    }

    @Bean
    public ManagedChannel profileChannel() {
        return ManagedChannelBuilder
                .forAddress("profiles-service", 6565)
                .usePlaintext()
                .build();
    }

    @Bean
    public ProfileServiceGrpc.ProfileServiceBlockingStub profileServiceBlockingStub(ManagedChannel profileChannel) {
        return ProfileServiceGrpc.newBlockingStub(profileChannel);
    }

    @Bean
    public ProfileGrpcClient profileGrpcClient(
            ProfileServiceGrpc.ProfileServiceBlockingStub profileServiceBlockingStub) {
        return new ProfileGrpcClient(profileServiceBlockingStub);
    }
}