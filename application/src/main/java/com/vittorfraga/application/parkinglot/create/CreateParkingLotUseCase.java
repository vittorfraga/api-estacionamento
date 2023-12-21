package com.vittorfraga.application.parkinglot.create;

import com.vittorfraga.application.UseCase;
import com.vittorfraga.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateParkingLotUseCase
        extends UseCase<CreteParkingLotInput, Either<Notification, CreteParkingLotOutput>> {
}
