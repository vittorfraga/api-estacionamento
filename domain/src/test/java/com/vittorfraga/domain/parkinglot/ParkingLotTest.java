package com.vittorfraga.domain.parkinglot;

import com.vittorfraga.domain.exceptions.DomainException;
import com.vittorfraga.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotTest {

    //Teste de criação Bem-Sucedida de um ParkingLot
    //Teste de criação de um ParkingLot com a propriedade 'name' null
    //Teste de criação de um ParkingLot com a propriedade 'name' empty
    //Teste de criação de um ParkingLot com a propriedade 'CNPJ' null
    //Teste de criação de um ParkingLot com a propriedade 'CNPJ' empty
    //Teste de criação de um ParkingLot com a propriedade 'address' null
    //Teste de criação de um ParkingLot com restrições de endereço não atendidas (city)
    //Teste de criação de um ParkingLot com restrições de endereço não atendidas (state)
    //Teste de criação de um ParkingLot com 'motorcycleSpaces' null
    //Teste de criação de um ParkingLot com 'motorcycleSpaces' negativo
    //Teste de criação de um ParkingLot com 'carSpaces' negativo
    //Teste de criação de um ParkingLot com 'carSpaces' null
    //Teste de update de um ParkingLot com sucesso
    //Teste de update de um ParkingLot com com 'carSpaces' negativo

    @Test
    public void givenAValidParams_whenCallNewParkingLot_thenInstantiateAnParkingLot() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        Assertions.assertNotNull(actualParkingLot);
        Assertions.assertNotNull(actualParkingLot.getId());
        Assertions.assertEquals(expectedName, actualParkingLot.getName());
        Assertions.assertEquals(expectedCNPJ, actualParkingLot.getCNPJ());
        Assertions.assertEquals(expectedAddress, actualParkingLot.getAddress());
        Assertions.assertEquals(expectedPhone, actualParkingLot.getPhone());
        Assertions.assertEquals(expectedMotorcycleSpaces, actualParkingLot.getMotorcycleSpaces());
        Assertions.assertEquals(expectedCarSpaces, actualParkingLot.getCarSpaces());
        Assertions.assertNotNull(actualParkingLot.getCreatedAt());
        Assertions.assertNotNull(actualParkingLot.getUpdatedAt());

    }

    @Test
    public void givenAnInvalidNullName_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final String expectedName = null;
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;


        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final String expectedName = "";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;


        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNullCnpj_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final String expectedCNPJ = null;
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PE");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'CNPJ' should not be null";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidEmptyCnpj_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final String expectedCNPJ = "";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PE");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'CNPJ' should not be empty";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNullAddress_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final Address expectedAddress = null;
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'address' should not be null";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidAddressConstraints_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("R", "0", "Ci", "XY");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'city' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidStateAddressConstraints_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rio", "0", "Cipo", "X");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'state' must be 2 characters";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidStreetAddressConstraints_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("", "0", "Cipo", "X");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'street' should not be empty";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidStreetEmptyAddressConstraints_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress(null, "0", "Cipo", "X");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'street' should not be null";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));
        System.out.println(actualParkingLot);
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }


    @Test
    public void givenAnInvalidNullMotorcycleSpaces_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "BA");
        final var expectedPhone = "+55 123456789";
        final Integer expectedMotorcycleSpaces = null;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'motorcycleSpaces' should not be null";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNegativeMotorcycleSpaces_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "BA");
        final var expectedPhone = "+55 123456789";
        final Integer expectedMotorcycleSpaces = -10;
        final var expectedCarSpaces = 30;
        final var expectedErrorMessage = "'motorcycleSpaces' should not be negative";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNegativeCarSpaces_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "RS");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = -5;
        final var expectedErrorMessage = "'carSpaces' should not be negative";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAnInvalidNullCarSpaces_whenCallNewParkingLotValidate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "RS");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final Integer expectedCarSpaces = null;
        final var expectedErrorMessage = "'carSpaces' should not be null";
        final var expectedErrorCount = 1;

        final var actualParkingLot = ParkingLot.newParkingLot(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

    @Test
    public void givenAValidParkingLot_whenCallUpdate_thenReturnParkingLotUpdated() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var aParkingLot = ParkingLot.newParkingLot("expectedNames", "56565656565656", expectedAddress, expectedPhone, 5, 5);

        Assertions.assertDoesNotThrow(() -> aParkingLot.validate(new ThrowsValidationHandler()));

        final var createdAt = aParkingLot.getCreatedAt();
        final var updatedAt = aParkingLot.getUpdatedAt();

        final var actualParkingLot = aParkingLot.update(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        Assertions.assertDoesNotThrow(() -> actualParkingLot.validate(new ThrowsValidationHandler()));


        Assertions.assertEquals(aParkingLot.getId(), actualParkingLot.getId());
        Assertions.assertEquals(expectedName, actualParkingLot.getName());
        Assertions.assertEquals(expectedCNPJ, actualParkingLot.getCNPJ());
        Assertions.assertEquals(expectedAddress, actualParkingLot.getAddress());
        Assertions.assertEquals(expectedPhone, actualParkingLot.getPhone());
        Assertions.assertEquals(expectedMotorcycleSpaces, actualParkingLot.getMotorcycleSpaces());
        Assertions.assertEquals(expectedCarSpaces, actualParkingLot.getCarSpaces());
        Assertions.assertTrue(actualParkingLot.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertEquals(createdAt, actualParkingLot.getCreatedAt());
    }

    @Test
    public void givenAnInvalidParkingLotParam_whenCallUpdate_thenShouldReturnError() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = -5;
        final var expectedErrorMessage = "'carSpaces' should not be negative";
        final var expectedErrorCount = 1;


        final var aParkingLot = ParkingLot.newParkingLot("expectedNames", "56565656565656", expectedAddress, expectedPhone, 5, 5);

        final var actualParkingLot = aParkingLot.update(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualParkingLot.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
    }

}