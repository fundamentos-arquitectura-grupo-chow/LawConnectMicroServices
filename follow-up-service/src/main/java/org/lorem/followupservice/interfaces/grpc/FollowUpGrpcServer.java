/*
package org.lorem.followupservice.interfaces.grpc;

import followup.*;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.followupservice.domain.model.aggregates.FollowUp;
import org.lorem.followupservice.domain.services.FollowUpQueryService;

import java.util.List;
import java.util.Optional;

@GRpcService
public class FollowUpGrpcServer extends FollowUpServiceGrpc.FollowUpServiceImplBase {

    private final FollowUpQueryService followUpQueryService;

    public FollowUpGrpcServer(FollowUpQueryService followUpQueryService) {
        this.followUpQueryService = followUpQueryService;
    }

    @Override
    public void getFollowUpsByConsultationId(GetFollowUpsByConsultationIdRequest request,
                                             StreamObserver<FollowUpsListResponse> responseObserver) {

        List<FollowUp> followUps = followUpQueryService.getFollowUpsByConsultationId(request.getConsultationId());

        FollowUpsListResponse.Builder responseBuilder = FollowUpsListResponse.newBuilder();
        for (FollowUp followUp : followUps) {
            FollowUpResponse followUpResponse = FollowUpResponse.newBuilder()
                    .setId(followUp.getId())
                    .setConsultationId(followUp.getConsultationId())
                    .setClientId(followUp.getClientId())
                    .setDescription(followUp.getDescription())
                    .setStatus(followUp.getStatus().name())
                    .setCreatedAt(followUp.getCreatedAt().toString())
                    .build();

            responseBuilder.addFollowUps(followUpResponse);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getFollowUpsByClientId(GetFollowUpsByClientIdRequest request,
                                       StreamObserver<FollowUpsListResponse> responseObserver) {

        List<FollowUp> followUps = followUpQueryService.getFollowUpsByClientId(request.getClientId());

        FollowUpsListResponse.Builder responseBuilder = FollowUpsListResponse.newBuilder();
        for (FollowUp followUp : followUps) {
            FollowUpResponse followUpResponse = FollowUpResponse.newBuilder()
                    .setId(followUp.getId())
                    .setConsultationId(followUp.getConsultationId())
                    .setClientId(followUp.getClientId())
                    .setDescription(followUp.getDescription())
                    .setStatus(followUp.getStatus().name())
                    .setCreatedAt(followUp.getCreatedAt().toString())
                    .build();

            responseBuilder.addFollowUps(followUpResponse);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }
}*/
