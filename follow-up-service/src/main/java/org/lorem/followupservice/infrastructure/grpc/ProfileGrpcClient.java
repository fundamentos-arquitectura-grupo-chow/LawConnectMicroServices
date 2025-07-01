package org.lorem.followupservice.infrastructure.grpc;

import org.springframework.stereotype.Service;
import profile.ClientDetailRequest;
import profile.ClientDetailResponse;
import profile.ProfileServiceGrpc;

import java.util.Optional;

@Service
public class ProfileGrpcClient {

    private final ProfileServiceGrpc.ProfileServiceBlockingStub stub;

    public ProfileGrpcClient(ProfileServiceGrpc.ProfileServiceBlockingStub stub) {
        this.stub = stub;
    }

    public Optional<String> getClientEmail(Long clientId) {
        try {
            ClientDetailResponse response = stub.getClientDetail(
                    ClientDetailRequest.newBuilder()
                            .setClientId(clientId)
                            .build()
            );
            return Optional.of(response.getEmail());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<String> getClientPhone(Long clientId) {
        try {
            ClientDetailResponse response = stub.getClientDetail(
                    ClientDetailRequest.newBuilder()
                            .setClientId(clientId)
                            .build()
            );
            return Optional.of(response.getPhoneNumber());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}