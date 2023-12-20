package com.vittorfraga.domain.parkinglot;

import com.vittorfraga.domain.ValueObject;

import java.util.Objects;

public class Address extends ValueObject {

    private String street;
    private String number;
    private String city;
    private String state;

    private Address(String street, String number, String city, String state) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
    }


    public static Address newAddress(final String aStreet,
                                     final String aNumber,
                                     final String aCity,
                                     final String aState) {
        return new Address(aStreet, aNumber, aCity, aState);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(number, address.number) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, number, city, state);
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}