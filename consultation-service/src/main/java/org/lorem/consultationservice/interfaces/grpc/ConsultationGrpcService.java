package org.lorem.consultationservice.interfaces.grpc;

import consultation.ConsultationIdResponse;
import consultation.ConsultationServiceGrpc;
import consultation.GetConsultationIdRequest;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.consultationservice.infrastructure.persistence.jpa.repositories.ConsultationRepository;

@GRpcService
public class ConsultationGrpcService extends ConsultationServiceGrpc.ConsultationServiceImplBase {

    private final ConsultationRepository consultationRepository;

    public ConsultationGrpcService(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public void getConsultationId(GetConsultationIdRequest request, StreamObserver<ConsultationIdResponse> responseObserver) {
        try {
            System.out.println("Recibida solicitud gRPC para consulta ID: " + request.getConsultationId());
            Long consultationId = request.getConsultationId();
            var existingConsultation = consultationRepository.findById(consultationId);

            if (existingConsultation.isPresent()) {
                System.out.println("Consulta encontrada: " + consultationId);
                var response = ConsultationIdResponse.newBuilder()
                        .setConsultationId(consultationId)
                        .build();
                responseObserver.onNext(response);
                responseObserver.onCompleted();
            } else {
                System.out.println("Consulta NO encontrada: " + consultationId);
                responseObserver.onError(new StatusRuntimeException(Status.NOT_FOUND
                        .withDescription("Consulta no encontrada: " + consultationId)));
            }
        } catch (Exception e) {
            System.err.println("Error en gRPC: " + e.getMessage());
            e.printStackTrace();
            responseObserver.onError(new StatusRuntimeException(Status.INTERNAL
                    .withDescription("Error al procesar la solicitud: " + e.getMessage())));
        }
    }

}
