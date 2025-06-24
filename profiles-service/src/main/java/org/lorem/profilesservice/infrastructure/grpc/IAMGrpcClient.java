package org.lorem.profilesservice.infrastructure.grpc;

import iam.IAMServiceGrpc;
import iam.GetUserIdRequest;
import iam.GetUserIdResponse;

public class IAMGrpcClient {

    private final IAMServiceGrpc.IAMServiceBlockingStub stub;

    public IAMGrpcClient(
            IAMServiceGrpc.IAMServiceBlockingStub stub
    ) {
        this.stub = stub;
    }

    public Long getUserIdByUsername(String username) {
        GetUserIdRequest request = GetUserIdRequest.newBuilder()
                .setUsername(username)
                .build();
        GetUserIdResponse response = stub.getUserIdByUsername(request);
        return response.getUserId();
    }
}