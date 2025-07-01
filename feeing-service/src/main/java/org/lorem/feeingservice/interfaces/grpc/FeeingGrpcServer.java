package org.lorem.feeingservice.infrastructure.grpc;

import feeing.*;
import feeing.FeeingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.lorem.feeingservice.domain.model.aggregates.Payment;
import org.lorem.feeingservice.domain.model.commands.CompletePaymentCommand;
import org.lorem.feeingservice.domain.model.commands.CreatePaymentCommand;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentByClientIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetAllPaymentsByConsultationIdQuery;
import org.lorem.feeingservice.domain.model.queries.GetPaymentByIdQuery;
import org.lorem.feeingservice.domain.services.PaymentCommandService;
import org.lorem.feeingservice.domain.services.PaymentQueryService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@GRpcService
public class FeeingGrpcServer extends FeeingServiceGrpc.FeeingServiceImplBase {

    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public FeeingGrpcServer(
            PaymentCommandService paymentCommandService,
            PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    @Override
    public void getPaymentById(GetPaymentByIdRequest request, StreamObserver<PaymentResponse> responseObserver) {
        Optional<Payment> paymentOptional = paymentQueryService.handle(new GetPaymentByIdQuery(request.getPaymentId()));

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            PaymentResponse response = PaymentResponse.newBuilder()
                    .setId(payment.getId())
                    .setClientId(payment.getClientId())
                    .setAmount(payment.getAmount().paymentAmountToString())
                    .setStatus(PaymentStatus.valueOf(payment.getStatus().name()))
                    .setConsultationId(payment.getConsultation())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("Pago no encontrado"));
        }
    }

    @Override
    public void getPaymentsByClientId(GetPaymentsByClientIdRequest request, StreamObserver<PaymentsListResponse> responseObserver) {
        List<Payment> payments = paymentQueryService.handle(new GetAllPaymentByClientIdQuery(request.getClientId()));

        PaymentsListResponse.Builder responseBuilder = PaymentsListResponse.newBuilder();
        for (Payment payment : payments) {
            PaymentResponse paymentResponse = PaymentResponse.newBuilder()
                    .setId(payment.getId())
                    .setClientId(payment.getClientId())
                    .setAmount(payment.getAmount().paymentAmountToString())
                    .setStatus(PaymentStatus.valueOf(payment.getStatus().name()))
                    .setConsultationId(payment.getConsultation())
                    .build();

            responseBuilder.addPayments(paymentResponse);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void getPaymentsByConsultationId(GetPaymentsByConsultationIdRequest request, StreamObserver<PaymentsListResponse> responseObserver) {
        List<Payment> payments = paymentQueryService.handle(
                new GetAllPaymentsByConsultationIdQuery(request.getConsultationId()));

        PaymentsListResponse.Builder responseBuilder = PaymentsListResponse.newBuilder();
        for (Payment payment : payments) {
            PaymentResponse paymentResponse = PaymentResponse.newBuilder()
                    .setId(payment.getId())
                    .setClientId(payment.getClientId())
                    .setAmount(payment.getAmount().paymentAmountToString())
                    .setStatus(PaymentStatus.valueOf(payment.getStatus().name()))
                    .setConsultationId(payment.getConsultation())
                    .build();

            responseBuilder.addPayments(paymentResponse);
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void createPayment(CreatePaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        Optional<Payment> paymentOptional = paymentCommandService.handle(
                new CreatePaymentCommand(
                        request.getConsultationId(),
                        request.getClientId(),
                        request.getAmount(),
                        request.getCurrency()
                )
        );

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            PaymentResponse response = PaymentResponse.newBuilder()
                    .setId(payment.getId())
                    .setClientId(payment.getClientId())
                    .setAmount(payment.getAmount().paymentAmountToString())
                    .setStatus(PaymentStatus.valueOf(payment.getStatus().name()))
                    .setConsultationId(payment.getConsultation())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("No se pudo crear el pago"));
        }
    }

    @Override
    public void completePayment(CompletePaymentRequest request, StreamObserver<PaymentResponse> responseObserver) {
        String[] dateParts = request.getExpirationDate().split("-");
        int year = Integer.parseInt(dateParts[0]);
        int month = Integer.parseInt(dateParts[1]);
        LocalDate expirationDate = LocalDate.of(year, month, 1);

        Optional<Payment> paymentOptional = paymentCommandService.handle(
                new CompletePaymentCommand(
                        request.getPaymentId(),
                        request.getCardNumber(),
                        expirationDate,
                        request.getCvv()
                )
        );

        if (paymentOptional.isPresent()) {
            Payment payment = paymentOptional.get();
            PaymentResponse response = PaymentResponse.newBuilder()
                    .setId(payment.getId())
                    .setClientId(payment.getClientId())
                    .setAmount(payment.getAmount().paymentAmountToString())
                    .setStatus(PaymentStatus.valueOf(payment.getStatus().name()))
                    .setConsultationId(payment.getConsultation())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new RuntimeException("No se pudo completar el pago"));
        }
    }
}