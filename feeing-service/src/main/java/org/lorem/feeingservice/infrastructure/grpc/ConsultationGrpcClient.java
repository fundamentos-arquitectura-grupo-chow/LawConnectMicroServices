package org.lorem.feeingservice.infrastructure.grpc;

import consultation.*;
import consultation.ConsultationServiceGrpc;


import org.lorem.feeingservice.domain.model.valueObjects.ConsultationDto;



import java.util.Optional;

public class ConsultationGrpcClient {

   private final ConsultationServiceGrpc.ConsultationServiceBlockingStub stub;

    public ConsultationGrpcClient(
            ConsultationServiceGrpc.ConsultationServiceBlockingStub stub
    ) {
        this.stub = stub;
    }

    public Long getConsultationId(Long consultationId) {
        GetConsultationIdRequest request = GetConsultationIdRequest.newBuilder()
                .setConsultationId(consultationId)
                .build();
        ConsultationIdResponse response = stub.getConsultationId(request);
        return response.getConsultationId();
    }



    public Optional<ConsultationDto> getConsultationByPaymentId(Long paymentId) {
        try {
            ConsultationResponse response = stub.getConsultationByPaymentId(
                    GetConsultationByPaymentIdRequest.newBuilder()
                            .setPaymentId(paymentId)
                            .build()
            );
            return Optional.of(mapToDto(response));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void changeConsultationStatus(Long consultationId) {
        stub.changeConsultationStatus(
                ChangeConsultationStatusRequest.newBuilder()
                        .setConsultationId(consultationId)
                        .build()
        );
    }

    private ConsultationDto mapToDto(ConsultationResponse response) {
        return new ConsultationDto(
                response.getId(),
                response.getClientId()
        );
    }
}
