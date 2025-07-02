package org.lorem.followupservice.interfaces.grpc;

import followup.FollowUpServiceGrpc;
import followup.CreateNotificationRequest;
import followup.EmptyResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.followupservice.interfaces.acl.FollowUpContextFacade;

@GRpcService
public class FollowUpGrpcService extends FollowUpServiceGrpc.FollowUpServiceImplBase {

    private final FollowUpContextFacade followUpContextFacade;

    public FollowUpGrpcService(FollowUpContextFacade followUpContextFacade) {
        this.followUpContextFacade = followUpContextFacade;
    }

    @Override
    public void createNotification(CreateNotificationRequest request, StreamObserver<EmptyResponse> responseObserver) {
        followUpContextFacade.createNotification(
                request.getTitle(),
                request.getDescription(),
                request.getClientId(),
                request.getConsultationId()
        );
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}