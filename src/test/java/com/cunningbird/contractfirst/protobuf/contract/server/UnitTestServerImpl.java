package com.cunningbird.contractfirst.protobuf.contract.server;

import com.cunningbird.contractfirst.protobuf.contract.*;
import com.cunningbird.contractfirst.protobuf.contract.Empty;
import com.cunningbird.contractfirst.protobuf.contract.Pet;
import com.cunningbird.contractfirst.protobuf.contract.PetID;
import com.cunningbird.contractfirst.protobuf.contract.Pets;
import com.cunningbird.contractfirst.protobuf.contract.PetstoreServiceGrpc;
import io.grpc.stub.StreamObserver;

public class UnitTestServerImpl extends PetstoreServiceGrpc.PetstoreServiceImplBase {

    @Override
    public void getPets(Empty request, StreamObserver<Pets> responseObserver) {
        Pet pet1 = Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build();
        Pet pet2 = Pet.newBuilder().setId(2).setName("Billy").setTag("Cat").build();

        Pets response = Pets.newBuilder().addPets(pet1).addPets(pet2).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getPetByID(PetID request, StreamObserver<Pet> responseObserver) {
        Pet response = Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void addPet(Pet request, StreamObserver<Pet> responseObserver) {
        Pet response = Pet.newBuilder().setId(1).setName("Van").setTag("Cat").build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
