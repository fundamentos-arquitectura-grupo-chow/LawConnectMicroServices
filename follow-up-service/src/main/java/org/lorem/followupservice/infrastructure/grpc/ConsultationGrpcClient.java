/*
package org.lorem.followupservice.infrastructure.grpc;

import consultation.ConsultationServiceGrpc;
import consultation.GetConsultationByIdRequest;
import consultation.ConsultationResponse;
import org.lorem.followupservice.domain.model.valueObjects.ConsultationDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultationGrpcClient {

    private final ConsultationServiceGrpc.ConsultationServiceBlockingStub stub;

    public ConsultationGrpcClient(ConsultationServiceGrpc.ConsultationServiceBlockingStub stub) {
        this.stub = stub;
    }

    public Optional<ConsultationDto> getConsultationById(Long consultationId) {
        try {
            ConsultationResponse response = stub.getConsultationById(
                    GetConsultationByIdRequest.newBuilder()
                            .setConsultationId(consultationId)
                            .build()
            );
            return Optional.of(mapToDto(response));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private ConsultationDto mapToDto(ConsultationResponse response) {
        return new ConsultationDto(
                response.getId(),
                response.getClientId()
        );
    }
}*/
