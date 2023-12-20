package com.vittorfraga.domain.parkinglot;

import com.vittorfraga.domain.validation.Error;
import com.vittorfraga.domain.validation.ValidationHandler;
import com.vittorfraga.domain.validation.Validator;

public class ParkingLotValidator extends Validator {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 255;
    private static final int CNPJ_LENGTH = 14;
    private static final int STATE_LENGTH = 2;

    private final ParkingLot parkingLot;

    protected ParkingLotValidator(final ParkingLot aParkingLot, final ValidationHandler aHandler) {
        super(aHandler);
        this.parkingLot = aParkingLot;
    }

    @Override
    public void validate() {
        checkNameConstraints();
        checkCnpjConstraints();
        checkAddressConstraints();
        checkPhoneConstraints();
        checkMotorcycleSpacesConstraints();
        checkCarSpacesConstraints();
    }

    private void checkNameConstraints() {
        checkStringConstraints("name", this.parkingLot.getName(), NAME_MIN_LENGTH, NAME_MAX_LENGTH);
    }

    private void checkCnpjConstraints() {
        checkStringConstraints("CNPJ", this.parkingLot.getCNPJ(), CNPJ_LENGTH, CNPJ_LENGTH);
    }

    private void checkAddressConstraints() {
        if (this.parkingLot.getAddress() == null) {
            this.validationHandler().append(new Error("'address' should not be null"));
        } else {
            final Address address = this.parkingLot.getAddress();
            checkStringConstraints("street", address.getStreet(), 1, NAME_MAX_LENGTH);
            checkStringConstraints("number", address.getNumber(), 1, Integer.MAX_VALUE);
            checkStringConstraints("city", address.getCity(), NAME_MIN_LENGTH, NAME_MAX_LENGTH);

            if (address.getState() == null) {
                this.validationHandler().append(new Error("'state' should not be null"));
            } else if (address.getState().length() != STATE_LENGTH) {
                this.validationHandler().append(new Error("'state' must be 2 characters"));
            }
        }
    }

    private void checkPhoneConstraints() {
        checkStringConstraints("phone", this.parkingLot.getPhone(), 1, NAME_MAX_LENGTH);
    }

    private void checkMotorcycleSpacesConstraints() {
        checkPositiveInteger("motorcycleSpaces", this.parkingLot.getMotorcycleSpaces());
    }

    private void checkCarSpacesConstraints() {
        checkPositiveInteger("carSpaces", this.parkingLot.getCarSpaces());
    }

    private void checkStringConstraints(String propertyName, String value, int minLength, int maxLength) {
        if (value == null) {
            this.validationHandler().append(new Error("'" + propertyName + "' should not be null"));
        } else if (value.isBlank()) {
            this.validationHandler().append(new Error("'" + propertyName + "' should not be empty"));
        } else {
            final int length = value.trim().length();
            if (length < minLength || length > maxLength) {
                this.validationHandler().append(new Error("'" + propertyName + "' must be between " + minLength + " and " + maxLength + " characters"));
            }
        }
    }

    private void checkIntegerConstraints(String propertyName, Integer value, int minValue, int maxValue) {
        if (value == null) {
            this.validationHandler().append(new Error("'" + propertyName + "' should not be null"));
        } else if (value < minValue || value > maxValue) {
            this.validationHandler().append(new Error("'" + propertyName + "' must be between " + minValue + " and " + maxValue));
        }
    }

    private void checkPositiveInteger(String propertyName, Integer value) {
        if (value == null) {
            this.validationHandler().append(new Error("'" + propertyName + "' should not be null"));
        } else if (value < 0) {
            this.validationHandler().append(new Error("'" + propertyName + "' should not be negative"));
        }
    }

}
