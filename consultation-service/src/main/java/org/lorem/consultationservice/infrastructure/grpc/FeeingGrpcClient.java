package org.lorem.consultationservice.infrastructure.grpc;

import feeing.CreatePaymentRequest;
import feeing.EmptyResponse;
import feeing.FeeingServiceGrpc;
import feeing.PaymentResponse;

public class FeeingGrpcClient {

    private final FeeingServiceGrpc.FeeingServiceBlockingStub stub;

    public FeeingGrpcClient(FeeingServiceGrpc.FeeingServiceBlockingStub stub) {
        this.stub = stub;
    }

    public void createPayment(CreatePaymentRequest request) {
        PaymentResponse response = stub.createPayment(request);
    }
}
