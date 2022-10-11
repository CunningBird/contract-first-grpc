package com.cunningbird.templates.contractfirstgrpc.server;

import com.cunningbird.templates.contractfirstgrpc.HelloRequest;
import com.cunningbird.templates.contractfirstgrpc.HelloResponse;
import com.cunningbird.templates.contractfirstgrpc.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UnitTestServerImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        String greeting = "Hello, " + request.getFirstName() + " " + request.getLastName();

        HelloResponse response = HelloResponse.newBuilder().setGreeting(greeting).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
