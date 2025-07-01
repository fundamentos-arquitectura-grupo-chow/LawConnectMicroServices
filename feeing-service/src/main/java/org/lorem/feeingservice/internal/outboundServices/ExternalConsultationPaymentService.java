package org.lorem.feeingservice.internal.outboundServices;

import org.lorem.feeingservice.domain.model.valueObjects.ConsultationDto;
import org.lorem.feeingservice.infrastructure.grpc.ConsultationGrpcClient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExternalConsultationPaymentService {

    private final ConsultationGrpcClient consultationGrpcClient;

    public ExternalConsultationPaymentService(ConsultationGrpcClient consultationGrpcClient) {
        this.consultationGrpcClient = consultationGrpcClient;
    }

    public void changeConsultationStatus(Long consultationId) {
        consultationGrpcClient.changeConsultationStatus(consultationId);
    }

    public Optional<ConsultationDto> getConsultationByPaymentId(Long paymentId) {
        return consultationGrpcClient.getConsultationByPaymentId(paymentId);
    }

    public Optional<ConsultationDto> getConsultationById(Long consultationId) {
        return consultationGrpcClient.getConsultationById(consultationId);
    }
}
