package com.cunningbird.templates.contractfirstgrpc;

import com.cunningbird.templates.contractfirstgrpc.server.UnitTestServerImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestContext {

    private final int port = 8080;

    private final String address = "localhost";

    private final Server server = ServerBuilder.forPort(port).addService(new UnitTestServerImpl()).build();

    @Test
    public void testHello1() throws IOException {
        server.start();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        HelloServiceGrpc.HelloServiceBlockingStub client = HelloServiceGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setFirstName("Van").setLastName("Darkhomle").build();

        HelloResponse helloResponse = client.hello(request);
        Assertions.assertEquals("Hello, Van Darkhomle", helloResponse.getGreeting());
        channel.shutdown();

        server.shutdown();
    }

    @Test
    public void testHello2() throws IOException {
        server.start();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        HelloServiceGrpc.HelloServiceBlockingStub client = HelloServiceGrpc.newBlockingStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setFirstName("Billy").setLastName("Herrington").build();

        HelloResponse helloResponse = client.hello(request);
        Assertions.assertEquals("Hello, Billy Herrington", helloResponse.getGreeting());
        channel.shutdown();

        server.shutdown();
    }
}
