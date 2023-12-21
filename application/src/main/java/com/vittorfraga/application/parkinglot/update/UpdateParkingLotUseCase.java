package com.vittorfraga.application.parkinglot.update;

import com.vittorfraga.application.UseCase;
import com.vittorfraga.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateParkingLotUseCase
        extends UseCase<UpdateParkingLotInput, Either<Notification, UpdateParkingLotOutput>> {
}
