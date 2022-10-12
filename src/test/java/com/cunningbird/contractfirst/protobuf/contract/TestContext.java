package com.cunningbird.contractfirst.protobuf.contract;

import com.cunningbird.contractfirst.protobuf.contract.server.UnitTestServerImpl;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestContext {

    private final int port = 8080;

    private final String address = "localhost";

    private final Server server = ServerBuilder.forPort(port).addService(new UnitTestServerImpl()).build();

    @Test
    public void testGetPets() throws IOException, ExecutionException, InterruptedException {
        com.cunningbird.contractfirst.protobuf.contract.Pets expected = com.cunningbird.contractfirst.protobuf.contract.Pets.newBuilder()
                .addPets(com.cunningbird.contractfirst.protobuf.contract.Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build())
                .addPets(com.cunningbird.contractfirst.protobuf.contract.Pet.newBuilder().setId(2).setName("Billy").setTag("Cat").build())
                .build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc.PetstoreServiceFutureStub client = com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc.newFutureStub(channel);
        com.cunningbird.contractfirst.protobuf.contract.Empty request = com.cunningbird.contractfirst.protobuf.contract.Empty.newBuilder().build();

        server.start();
        ListenableFuture<com.cunningbird.contractfirst.protobuf.contract.Pets> actual = client.getPets(request);
        Assertions.assertEquals(expected, actual.get());
        channel.shutdown();
        server.shutdown();
    }

    @Test
    public void testGetPetByID() throws IOException, ExecutionException, InterruptedException {
        com.cunningbird.contractfirst.protobuf.contract.Pet expected = com.cunningbird.contractfirst.protobuf.contract.Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc.PetstoreServiceFutureStub client = com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc.newFutureStub(channel);
        com.cunningbird.contractfirst.protobuf.contract.PetID request = com.cunningbird.contractfirst.protobuf.contract.PetID.newBuilder().setId(1).build();

        server.start();
        ListenableFuture<com.cunningbird.contractfirst.protobuf.contract.Pet> actual = client.getPetByID(request);
        Assertions.assertEquals(expected, actual.get());
        channel.shutdown();
        server.shutdown();
    }

    @Test
    public void testAddPet() throws IOException, ExecutionException, InterruptedException {
        com.cunningbird.contractfirst.protobuf.contract.Pet expected = com.cunningbird.contractfirst.protobuf.contract.Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build();

        ManagedChannel channel = ManagedChannelBuilder.forAddress(address, port).usePlaintext().build();
        com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc.PetstoreServiceFutureStub client = com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc.newFutureStub(channel);
        com.cunningbird.contractfirst.protobuf.contract.Pet request = com.cunningbird.contractfirst.protobuf.contract.Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build();

        server.start();
        ListenableFuture<com.cunningbird.contractfirst.protobuf.contract.Pet> actual = client.addPet(request);
        Assertions.assertEquals(expected, actual.get());
        channel.shutdown();
        server.shutdown();
    }
}
