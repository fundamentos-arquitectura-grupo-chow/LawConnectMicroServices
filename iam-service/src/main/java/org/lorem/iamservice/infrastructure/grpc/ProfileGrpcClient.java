package org.lorem.iamservice.infrastructure.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import profile.ProfileServiceGrpc;
import profile.ClientRequest;
import profile.EmptyResponse;

public class ProfileGrpcClient {

    private final ProfileServiceGrpc.ProfileServiceBlockingStub stub;

    public ProfileGrpcClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        this.stub = ProfileServiceGrpc.newBlockingStub(channel);
    }

    public void createClient(ClientRequest request) {
        EmptyResponse response = stub.createClient(request);
        System.out.println("Respuesta: " + response);
    }
}