package ru.pivovarov.transferservice.validator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pivovarov.transferservice.exception.ErrorException;
import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.TransferRq;

@SpringBootTest
class ValidatorTest {

    Validator validator = new Validator();
    static TransferRq transferRqMock;
    static ConfirmRq confirmRqMock;

    @BeforeAll
    static void setUp() {

        transferRqMock = Mockito.mock(TransferRq.class);

        confirmRqMock = Mockito.mock(ConfirmRq.class);
    }

    @Test
    public void checkValidInvalidCardFromCVVSizeZero() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardFromCVV()).thenReturn("");
        });
    }

    @Test
    public void checkValidInvalidCardFromCVVNull() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardFromCVV()).thenReturn(null);
        });
    }

    @Test
    public void checkValidInvalidCardFromNumberSizeZero() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardFromNumber()).thenReturn("");
        });
    }

    @Test
    public void checkValidInvalidCardFromNumberNull() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardFromNumber()).thenReturn(null);
        });
    }

    @Test
    public void checkValidInvalidCardFromValidTillSizeZero() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardFromValidTill()).thenReturn("");
        });
    }

    @Test
    public void checkValidInvalidCardFromValidTillNull() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardFromValidTill()).thenReturn(null);
        });
    }

    @Test
    public void checkValidInvalidCardToNumberZero() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardToNumber()).thenReturn("");
        });
    }

    @Test
    public void checkValidInvalidCardToNumberNull() {
        Assertions.assertThrows(ErrorException.class, () -> {
            validator.checkValid(transferRqMock);
            Mockito.when(transferRqMock.cardToNumber()).thenReturn(null);
        });
    }

}