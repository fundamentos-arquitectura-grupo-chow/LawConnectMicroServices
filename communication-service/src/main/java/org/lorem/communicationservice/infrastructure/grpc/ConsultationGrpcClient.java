package org.lorem.communicationservice.infrastructure.grpc;

import consultation.ConsultationIdRequest;
import consultation.ConsultationServiceGrpc;
import consultation.ExistConsultationResponse;
import consultation.GetClientIdResponse;

public class ConsultationGrpcClient {

    private final ConsultationServiceGrpc.ConsultationServiceBlockingStub stub;

    public ConsultationGrpcClient(ConsultationServiceGrpc.ConsultationServiceBlockingStub stub) {
        this.stub = stub;
    }

    public long getClientIdByConsultationId(long consultationId) {
        ConsultationIdRequest request = ConsultationIdRequest.newBuilder()
                .setConsultationId(consultationId)
                .build();
        GetClientIdResponse response = stub.getClientIdByConsultationId(request);
        return response.getClientId();
    }

    public boolean existConsultationById(long consultationId) {
        ConsultationIdRequest request = ConsultationIdRequest.newBuilder()
                .setConsultationId(consultationId)
                .build();
        ExistConsultationResponse response = stub.existConsultationById(request);
        return response.getExists();
    }
}