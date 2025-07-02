package org.lorem.profilesservice.interfaces.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.profilesservice.interfaces.acl.ProfileContextFacade;
import profile.ProfileServiceGrpc;
import profile.ClientRequest;
import profile.LawyerRequest;
import profile.EmptyResponse;
import org.springframework.stereotype.Service;

@GRpcService
public class ProfileGrpcService extends ProfileServiceGrpc.ProfileServiceImplBase {

    private final ProfileContextFacade profileContextFacade;

    public ProfileGrpcService(ProfileContextFacade profileContextFacade) {
        this.profileContextFacade = profileContextFacade;
    }

    @Override
    public void createClient(ClientRequest request, StreamObserver<EmptyResponse> responseObserver) {
        profileContextFacade.createClient(
                request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPhoneNumber(), request.getAddress(), request.getDni(), request.getImageUrl()
        );
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void createLawyer(LawyerRequest request, StreamObserver<EmptyResponse> responseObserver) {
        profileContextFacade.createLawyer(
                request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPhoneNumber(), request.getAddress(), request.getDni(), request.getImageUrl()
        );
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
