package com.vittorfraga.application.parkinglot.update;

import com.vittorfraga.domain.parkinglot.Address;

public record UpdateParkingLotInput(
        String id,
        String name,
        String CNPJ,
        Address address,
        String phone,
        Integer motorcycleSpaces,
        Integer carSpaces
) {
    public static UpdateParkingLotInput with(
            final String anId,
            final String aName,
            final String aCNPJ,
            final Address anAddress,
            final String aPhone,
            final Integer aMotorcycleSpaces,
            final Integer aCarSpaces
    ) {
        return new UpdateParkingLotInput(anId, aName, aCNPJ, anAddress, aPhone, aMotorcycleSpaces, aCarSpaces);
    }
}
