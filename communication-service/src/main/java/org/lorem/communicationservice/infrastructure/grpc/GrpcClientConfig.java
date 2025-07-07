package org.lorem.communicationservice.infrastructure.grpc;

import consultation.ConsultationServiceGrpc;
import followup.FollowUpServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public FollowUpGrpcClient followUpGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("follow-up-service", 6565)
                .usePlaintext()
                .build();
        return new FollowUpGrpcClient(FollowUpServiceGrpc.newBlockingStub(channel));
    }

    @Bean
    public ConsultationGrpcClient consultationGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("consultation-service", 6566)
                .usePlaintext()
                .build();
        return new ConsultationGrpcClient(ConsultationServiceGrpc.newBlockingStub(channel));
    }
}