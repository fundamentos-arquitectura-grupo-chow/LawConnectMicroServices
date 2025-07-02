package org.lorem.consultationservice.interfaces.grpc;

import consultation.ConsultationServiceGrpc;
import consultation.ConsultationIdRequest;
import consultation.GetClientIdResponse;
import consultation.ExistConsultationResponse;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.consultationservice.interfaces.acl.ConsultationContextFacade;

@GRpcService
public class ConsultationGrpcService extends ConsultationServiceGrpc.ConsultationServiceImplBase {

    private final ConsultationContextFacade consultationContextFacade;

    public ConsultationGrpcService(ConsultationContextFacade consultationContextFacade) {
        this.consultationContextFacade = consultationContextFacade;
    }

    @Override
    public void getClientIdByConsultationId(ConsultationIdRequest request, StreamObserver<GetClientIdResponse> responseObserver) {
        Long clientId = consultationContextFacade.getClientIdById(request.getConsultationId());
        GetClientIdResponse response = GetClientIdResponse.newBuilder()
                .setClientId(clientId)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void existConsultationById(ConsultationIdRequest request, StreamObserver<ExistConsultationResponse> responseObserver) {
        boolean exists = consultationContextFacade.existConsultationById(request.getConsultationId());
        ExistConsultationResponse response = ExistConsultationResponse.newBuilder()
                .setExists(exists)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}