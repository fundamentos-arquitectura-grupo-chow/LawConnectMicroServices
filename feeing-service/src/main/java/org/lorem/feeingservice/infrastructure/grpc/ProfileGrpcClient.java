package org.lorem.feeingservice.infrastructure.grpc;

import org.springframework.stereotype.Service;
import profile.ClientRequest;
import profile.EmptyResponse;
import profile.LawyerRequest;
import profile.ProfileServiceGrpc;

@Service
public class ProfileGrpcClient {

    private final ProfileServiceGrpc.ProfileServiceBlockingStub stub;

    public ProfileGrpcClient(ProfileServiceGrpc.ProfileServiceBlockingStub stub) {
        this.stub = stub;
    }

    public void createClient(String firstName, String lastName, String email,
                             String phoneNumber, String address, String dni, String imageUrl) {
        ClientRequest request = ClientRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setDni(dni)
                .setImageUrl(imageUrl)
                .build();

        EmptyResponse response = stub.createClient(request);
    }

    public void createLawyer(String firstName, String lastName, String email,
                             String phoneNumber, String address, String dni, String imageUrl) {
        LawyerRequest request = LawyerRequest.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setPhoneNumber(phoneNumber)
                .setAddress(address)
                .setDni(dni)
                .setImageUrl(imageUrl)
                .build();

        EmptyResponse response = stub.createLawyer(request);
    }
}