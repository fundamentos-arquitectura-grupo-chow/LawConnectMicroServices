package org.lorem.feeingservice.infrastructure.grpc;

//import consultation.ConsultationServiceGrpc;
//import consultation.GetConsultationByIdRequest;
//import consultation.GetConsultationByPaymentIdRequest;
//import consultation.ChangeConsultationStatusRequest;
//import consultation.ConsultationResponse;
//import org.lorem.feeingservice.domain.model.valueObjects.ConsultationDto;
// org.springframework.stereotype.Service;

import org.lorem.feeingservice.domain.model.valueObjects.ConsultationDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*
@Service
public class ConsultationGrpcClient {

    private final ConsultationServiceGrpc.ConsultationServiceBlockingStub stub;

    public ConsultationGrpcClient(
            ConsultationServiceGrpc.ConsultationServiceBlockingStub stub
    ) {
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
}*/
