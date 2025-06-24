package org.lorem.profilesservice.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import iam.IAMServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Bean
    public IAMGrpcClient iamGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("iam-service", 6565)
                .usePlaintext()
                .build();
        return new IAMGrpcClient(IAMServiceGrpc.newBlockingStub(channel));
    }
}