package com.vittorfraga.application.parkinglot.create;

import com.vittorfraga.domain.parkinglot.ParkingLot;
import com.vittorfraga.domain.parkinglot.ParkingLotGateway;
import com.vittorfraga.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.control.Either.left;

public class DefaultCreateParkingLotUseCase extends CreateParkingLotUseCase {

    private final ParkingLotGateway gateway;

    public DefaultCreateParkingLotUseCase(ParkingLotGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, CreteParkingLotOutput> execute(CreteParkingLotInput anInput) {
        final var aName = anInput.name();
        final var aCNPJ = anInput.CNPJ();
        final var anAddress = anInput.address();
        final var aPhone = anInput.phone();
        final var aMotorcycleSpaces = anInput.motorcycleSpaces();
        final var aCarSpaces = anInput.carSpaces();

        final var notification = Notification.create();
        final var aParkingLot = ParkingLot.newParkingLot(aName, aCNPJ, anAddress, aPhone, aMotorcycleSpaces, aCarSpaces);
        aParkingLot.validate(notification);


        return notification.hasError() ? left(notification) : create(aParkingLot);
    }

    private Either<Notification, CreteParkingLotOutput> create(final ParkingLot aParkingLot) {
        return API.Try(() -> this.gateway.create(aParkingLot))
                .toEither()
                .bimap(Notification::create, CreteParkingLotOutput::from);

    }
}
