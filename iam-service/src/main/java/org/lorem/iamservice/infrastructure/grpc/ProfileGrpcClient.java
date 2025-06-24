package org.lorem.iamservice.infrastructure.grpc;

import profile.LawyerRequest;
import profile.ProfileServiceGrpc;
import profile.ClientRequest;
import profile.EmptyResponse;

public class ProfileGrpcClient {

    private final ProfileServiceGrpc.ProfileServiceBlockingStub stub;

    public ProfileGrpcClient(ProfileServiceGrpc.ProfileServiceBlockingStub stub) {
        this.stub = stub;
    }

    public void createClient(ClientRequest request) {
        EmptyResponse response = stub.createClient(request);
        System.out.println("Respuesta: " + response);
    }

    public void createLawyer(LawyerRequest request) {
        EmptyResponse response = stub.createLawyer(request);
        System.out.println("Respuesta: " + response);
    }
}