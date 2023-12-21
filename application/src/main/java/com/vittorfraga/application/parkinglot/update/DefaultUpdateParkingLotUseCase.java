package com.vittorfraga.application.parkinglot.update;

import com.vittorfraga.domain.exceptions.DomainException;
import com.vittorfraga.domain.parkinglot.ParkingLot;
import com.vittorfraga.domain.parkinglot.ParkingLotGateway;
import com.vittorfraga.domain.parkinglot.ParkingLotID;
import com.vittorfraga.domain.validation.Error;
import com.vittorfraga.domain.validation.handler.Notification;
import io.vavr.API;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultUpdateParkingLotUseCase extends UpdateParkingLotUseCase {

    private final ParkingLotGateway gateway;

    public DefaultUpdateParkingLotUseCase(ParkingLotGateway gateway) {
        this.gateway = Objects.requireNonNull(gateway);
    }

    @Override
    public Either<Notification, UpdateParkingLotOutput> execute(final UpdateParkingLotInput anInput) {
        final var anId = ParkingLotID.from(anInput.id());
        final var aName = anInput.name();
        final var aCNPJ = anInput.CNPJ();
        final var anAddress = anInput.address();
        final var aPhone = anInput.phone();
        final var aMotorcycleSpaces = anInput.motorcycleSpaces();
        final var aCarSpaces = anInput.carSpaces();

        final var aParkingLot = gateway.findById(anId).orElseThrow(notFound(anId));

        final var notification = Notification.create();

        aParkingLot.update(aName, aCNPJ, anAddress, aPhone, aMotorcycleSpaces, aCarSpaces).validate(notification);

        return notification.hasError() ? API.Left(notification) : update(aParkingLot);
    }

    private Either<Notification, UpdateParkingLotOutput> update(final ParkingLot aParkingLot) {
        return API.Try(() -> this.gateway.update(aParkingLot)).toEither()
                .bimap(Notification::create, UpdateParkingLotOutput::from);
    }

    private static Supplier<DomainException> notFound(final ParkingLotID anId) {
        return () -> DomainException.with(
                new Error("Parking lot with with ID %s was not found".formatted(anId.getValue())));
    }
}
