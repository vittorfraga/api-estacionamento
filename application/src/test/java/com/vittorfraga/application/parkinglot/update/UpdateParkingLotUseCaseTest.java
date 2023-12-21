package com.vittorfraga.application.parkinglot.update;

import com.vittorfraga.domain.parkinglot.Address;
import com.vittorfraga.domain.parkinglot.ParkingLot;
import com.vittorfraga.domain.parkinglot.ParkingLotGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class UpdateParkingLotUseCaseTest {

    @InjectMocks
    private DefaultUpdateParkingLotUseCase useCase;

    @Mock
    private ParkingLotGateway gateway;

    @Test
    public void givenAValidCommand_whenCallsUpdateParkingLot_shouldReturnParkingLotId() {
        final var someAddress = Address.newAddress("Rua augusta", "456", "CidadeY", "AM");
        final var aParkingLot = ParkingLot.newParkingLot("name", "33333333333333", someAddress, "51987456321", 10, 10);
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;
        final var expectedId = aParkingLot.getId();

        final var anInput =
                UpdateParkingLotInput.with(expectedId.getValue(), expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        when(gateway.findById(Mockito.eq(expectedId))).thenReturn(Optional.of(aParkingLot.clone()));

        when(gateway.update(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(anInput).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(gateway, times(1)).findById((eq(expectedId)));

        verify(gateway, times(1)).update(argThat(updatedParkingLot ->
                Objects.equals(expectedName, updatedParkingLot.getName())
                        && Objects.equals(expectedCNPJ, updatedParkingLot.getCNPJ())
                        && Objects.equals(expectedAddress, updatedParkingLot.getAddress())
                        && Objects.equals(expectedPhone, updatedParkingLot.getPhone())
                        && Objects.equals(expectedMotorcycleSpaces, updatedParkingLot.getMotorcycleSpaces())
                        && Objects.equals(expectedCarSpaces, updatedParkingLot.getCarSpaces())
                        && Objects.equals(expectedId, updatedParkingLot.getId())
                        && Objects.equals(aParkingLot.getCreatedAt(), updatedParkingLot.getCreatedAt())
                        && aParkingLot.getCreatedAt().isBefore(updatedParkingLot.getUpdatedAt())
        ));
    }

    @Test
    public void givenAnInvalidName_whenCallsUpdateParkingLot_shouldReturnDomainException() {
        final var someAddress = Address.newAddress("Rua augusta", "456", "CidadeY", "AM");
        final var aParkingLot = ParkingLot.newParkingLot("name", "33333333333333", someAddress, "51987456321", 10, 10);
        final var expectedId = aParkingLot.getId();
        final var expectedName = "E ";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        final var anInput =
                UpdateParkingLotInput.with(expectedId.getValue(), expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        when(gateway.findById(Mockito.eq(expectedId))).thenReturn(Optional.of(aParkingLot.clone()));

        final var notification = useCase.execute(anInput).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAnInvalidCNPJAndPhone_whenCallsUpdateParkingLot_shouldReturnDomainException() {
        final var someAddress = Address.newAddress("Rua augusta", "456", "CidadeY", "AM");
        final var aParkingLot = ParkingLot.newParkingLot("name", "33333333333333", someAddress, "51987456321", 10, 10);
        final var expectedId = aParkingLot.getId();
        final var expectedName = "Establishment";
        final var expectedCNPJ = "123456789034";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final String expectedPhone = "";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var expectedErrorMessage1 = "'phone' should not be empty";
        final var expectedErrorMessage2 = "'CNPJ' must be between 14 and 14 characters";
        final var expectedErrorCount = 2;

        final var anInput =
                UpdateParkingLotInput.with(expectedId.getValue(), expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        when(gateway.findById(Mockito.eq(expectedId))).thenReturn(Optional.of(aParkingLot.clone()));

        final var notification = useCase.execute(anInput).getLeft();

        Assertions.assertEquals(expectedErrorMessage2, notification.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorMessage1, notification.getErrors().get(1).message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }

    @Test
    public void givenAnInvalidCNPJAndPhonee_whenCallsUpdateParkingLot_shouldReturnDomainException() {
        final var someAddress = Address.newAddress("Rua augusta", "123", "CidadeY", "SP");
        final var aParkingLot = ParkingLot.newParkingLot("name", "33333333333333", someAddress, "51987456321", 10, 10);
        final var expectedId = aParkingLot.getId();
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", null, "CidadeX", "Brasilia");
        final String expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = -30;

        final var expectedErrorMessage1 = "'number' should not be null";
        final var expectedErrorMessage2 = "'state' must be 2 characters";
        final var expectedErrorMessage3 = "'carSpaces' should not be negative";
        final var expectedErrorCount = 3;

        final var anInput =
                UpdateParkingLotInput.with(expectedId.getValue(), expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        when(gateway.findById(Mockito.eq(expectedId))).thenReturn(Optional.of(aParkingLot.clone()));

        final var notification = useCase.execute(anInput).getLeft();
        System.out.println(notification.getErrors().getLast());


        Assertions.assertEquals(expectedErrorMessage1, notification.getErrors().get(0).message());
        Assertions.assertEquals(expectedErrorMessage2, notification.getErrors().get(1).message());
        Assertions.assertEquals(expectedErrorMessage3, notification.getErrors().get(2).message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        Mockito.verify(gateway, times(0)).update(any());
    }


}
