package org.lorem.legalcaseservice.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import followup.FollowUpServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Bean
    public FollowUpGrpcClient followUpGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("follow-up-service", 6565)
                .usePlaintext()
                .build();
        return new FollowUpGrpcClient(FollowUpServiceGrpc.newBlockingStub(channel));
    }
}