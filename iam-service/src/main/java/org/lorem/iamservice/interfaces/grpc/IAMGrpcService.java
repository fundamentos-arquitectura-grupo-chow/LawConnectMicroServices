package org.lorem.iamservice.interfaces.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.iamservice.interfaces.acl.IamContextFacade;
import iam.IAMServiceGrpc;
import iam.GetUserIdRequest;
import iam.GetUserIdResponse;

@GRpcService
public class IAMGrpcService extends IAMServiceGrpc.IAMServiceImplBase {

    private final IamContextFacade iamContextFacade;

    public IAMGrpcService(IamContextFacade iamContextFacade) {
        this.iamContextFacade = iamContextFacade;
    }

    @Override
    public void getUserIdByUsername(GetUserIdRequest request, StreamObserver<GetUserIdResponse> responseObserver) {
        Long userId = iamContextFacade.fetchUserIdByUsername(request.getUsername());
        GetUserIdResponse response = GetUserIdResponse.newBuilder()
                .setUserId(userId != null ? userId : 0L)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}