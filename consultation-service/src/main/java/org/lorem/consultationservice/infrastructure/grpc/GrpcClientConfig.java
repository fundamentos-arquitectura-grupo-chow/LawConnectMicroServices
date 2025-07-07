package org.lorem.consultationservice.infrastructure.grpc;

import feeing.FeeingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClientConfig {

    @Bean
    public FeeingGrpcClient consultationGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("feeing-service", 6565)
                .usePlaintext()
                .build();
        return new FeeingGrpcClient(FeeingServiceGrpc.newBlockingStub(channel));
    }
}
