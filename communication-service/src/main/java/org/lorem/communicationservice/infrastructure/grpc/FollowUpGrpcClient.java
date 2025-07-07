package org.lorem.communicationservice.infrastructure.grpc;

import followup.CreateNotificationRequest;
import followup.EmptyResponse;
import followup.FollowUpServiceGrpc;

public class FollowUpGrpcClient {

    private final FollowUpServiceGrpc.FollowUpServiceBlockingStub stub;

    public FollowUpGrpcClient(FollowUpServiceGrpc.FollowUpServiceBlockingStub stub) {
        this.stub = stub;
    }

    public void createNotification(String title, String description, long clientId, long consultationId) {
        CreateNotificationRequest request = CreateNotificationRequest.newBuilder()
                .setTitle(title)
                .setDescription(description)
                .setClientId(clientId)
                .setConsultationId(consultationId)
                .build();
        EmptyResponse response = stub.createNotification(request);
        System.out.println("Respuesta: " + response);
    }
}