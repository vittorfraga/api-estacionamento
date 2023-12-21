package com.vittorfraga.application.parkinglot.update;

import com.vittorfraga.domain.parkinglot.ParkingLot;
import com.vittorfraga.domain.parkinglot.ParkingLotID;

public record UpdateParkingLotOutput(ParkingLotID id) {
    public static UpdateParkingLotOutput from(final ParkingLot aParkingLot) {
        return new UpdateParkingLotOutput(aParkingLot.getId());
    }
}
