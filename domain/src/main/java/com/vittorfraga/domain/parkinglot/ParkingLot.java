package com.vittorfraga.domain.parkinglot;

import com.vittorfraga.domain.AggregateRoot;
import com.vittorfraga.domain.validation.ValidationHandler;

import java.time.Instant;

public class ParkingLot extends AggregateRoot<ParkingLotID> implements Cloneable {

    private String name;
    private String CNPJ;
    private Address address;
    private String phone;
    private Integer motorcycleSpaces;
    private Integer carSpaces;
    private Instant createdAt;
    private Instant updatedAt;

    private ParkingLot(
            final ParkingLotID anId,
            final String aName,
            final String aCNPJ,
            final Address anAddress,
            final String aPhone,
            final Integer aMotorcycleSpaces,
            final Integer aCarSpaces,
            final Instant aCreationDate,
            final Instant anUpdateDate) {
        super(anId);
        this.name = aName;
        this.CNPJ = aCNPJ;
        this.address = anAddress;
        this.phone = aPhone;
        this.motorcycleSpaces = aMotorcycleSpaces;
        this.carSpaces = aCarSpaces;
        this.createdAt = aCreationDate;
        this.updatedAt = anUpdateDate;
    }

    public static ParkingLot newParkingLot(
            final String aName,
            final String aCNPJ,
            final Address anAddress,
            final String aPhone,
            final Integer aMotorcycleSpaces,
            final Integer aCarSpaces
    ) {
        final var id = ParkingLotID.unique();
        final var now = Instant.now();
        return new ParkingLot(id, aName, aCNPJ, anAddress, aPhone, aMotorcycleSpaces, aCarSpaces, now, now);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new ParkingLotValidator(this, handler).validate();
    }

    public ParkingLot update(final String aName,
                             final String aCNPJ,
                             final Address anAddress,
                             final String aPhone,
                             final Integer aMotorcycleSpaces,
                             final Integer aCarSpaces) {
        this.name = (aName != null) ? aName : this.name;
        this.CNPJ = (aCNPJ != null) ? aCNPJ : this.CNPJ;
        this.address = (anAddress != null) ? anAddress : this.address;
        this.phone = (aPhone != null) ? aPhone : this.phone;
        this.motorcycleSpaces = (aMotorcycleSpaces != null) ? aMotorcycleSpaces : this.motorcycleSpaces;
        this.carSpaces = (aCarSpaces != null) ? aCarSpaces : this.carSpaces;

        this.updatedAt = Instant.now();
        return this;
    }

    public ParkingLotID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Integer getMotorcycleSpaces() {
        return motorcycleSpaces;
    }

    public Integer getCarSpaces() {
        return carSpaces;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", motorcycleSpaces=" + motorcycleSpaces +
                ", carSpaces=" + carSpaces +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public ParkingLot clone() {
        try {
            return (ParkingLot) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
