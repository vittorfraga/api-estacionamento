package com.vittorfraga.application.parkinglot.create;

import com.vittorfraga.domain.parkinglot.Address;

public record CreteParkingLotInput(
        String name,
        String CNPJ,
        Address address,
        String phone,
        Integer motorcycleSpaces,
        Integer carSpaces
) {
    public static CreteParkingLotInput with(
            final String aName,
            final String aCNPJ,
            final Address anAddress,
            final String aPhone,
            final Integer aMotorcycleSpaces,
            final Integer aCarSpaces
    ) {
        return new CreteParkingLotInput(aName, aCNPJ, anAddress, aPhone, aMotorcycleSpaces, aCarSpaces);
    }
}
