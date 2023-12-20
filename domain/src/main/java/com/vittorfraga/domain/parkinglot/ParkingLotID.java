package com.vittorfraga.domain.parkinglot;

import com.vittorfraga.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

public class ParkingLotID extends Identifier {

    private final String value;

    private ParkingLotID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ParkingLotID unique() {
        return ParkingLotID.from(UUID.randomUUID());
    }

    public static ParkingLotID from(final String anId) {
        return new ParkingLotID(anId);
    }

    public static ParkingLotID from(final UUID anId) {
        return new ParkingLotID(anId.toString().toLowerCase());
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingLotID that = (ParkingLotID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
