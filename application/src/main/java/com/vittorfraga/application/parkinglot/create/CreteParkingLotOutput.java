package com.vittorfraga.application.parkinglot.create;

import com.vittorfraga.domain.parkinglot.ParkingLot;
import com.vittorfraga.domain.parkinglot.ParkingLotID;

public record CreteParkingLotOutput(
        ParkingLotID id
) {


    public static CreteParkingLotOutput from(final ParkingLot aParkingLot) {
        return new CreteParkingLotOutput(aParkingLot.getId());
    }
}
