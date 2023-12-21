package com.vittorfraga.application.parkinglot.create;

import com.vittorfraga.domain.parkinglot.Address;
import com.vittorfraga.domain.parkinglot.ParkingLotGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateParkingLotUseCaseTest {
    @InjectMocks
    private DefaultCreateParkingLotUseCase useCase;

    @Mock
    private ParkingLotGateway gateway;


// 1. Teste do caminho feliz
// 2. Teste passando uma propriedade inválida (name)
// 3. Teste criando uma categoria inativa
// 4. Teste simulando um erro generico vindo do gateway

    @Test
    public void givenAValidCommand_whenCallsCreateParkingLot_shouldReturnParkingLotId() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var anInput =
                CreteParkingLotInput.with(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        when(gateway.create(any()))
                .thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(anInput).get();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        verify(gateway, times(1)).create(argThat(aParkingLot ->
                Objects.equals(expectedName, aParkingLot.getName())
                        && Objects.equals(expectedCNPJ, aParkingLot.getCNPJ())
                        && Objects.equals(expectedAddress, aParkingLot.getAddress())
                        && Objects.equals(expectedPhone, aParkingLot.getPhone())
                        && Objects.equals(expectedMotorcycleSpaces, aParkingLot.getMotorcycleSpaces())
                        && Objects.equals(expectedCarSpaces, aParkingLot.getCarSpaces())
                        && Objects.nonNull(aParkingLot.getId())
                        && Objects.nonNull(aParkingLot.getCreatedAt())
                        && Objects.nonNull(aParkingLot.getUpdatedAt())
        ));
    }

    @Test
    public void givenAnInvalidName_whenCallsCreateParkingLot_shouldReturnDomainException() {
        final var expectedName = "E ";
        final var expectedCNPJ = "12345678901234";
        final var expectedAddress = Address.newAddress("Rua ABC", "123", "CidadeX", "PR");
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        final var anInput =
                CreteParkingLotInput.with(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var notification = useCase.execute(anInput).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());

        Mockito.verify(gateway, times(0)).create(any());
    }

    @Test
    public void givenAnInvalidAdress_whenCallsCreateParkingLot_shouldReturnDomainException() {
        final var expectedName = "Establishment";
        final var expectedCNPJ = "12345678901234";
        final Address expectedAddress = null;
        final var expectedPhone = "+55 123456789";
        final var expectedMotorcycleSpaces = 20;
        final var expectedCarSpaces = 30;

        final var expectedErrorMessage = "'address' should not be null";
        final var expectedErrorCount = 1;

        final var anInput =
                CreteParkingLotInput.with(expectedName, expectedCNPJ, expectedAddress, expectedPhone, expectedMotorcycleSpaces, expectedCarSpaces);

        final var notification = useCase.execute(anInput).getLeft();

        Assertions.assertEquals(expectedErrorMessage, notification.getErrors().getFirst().message());
        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
    }
}