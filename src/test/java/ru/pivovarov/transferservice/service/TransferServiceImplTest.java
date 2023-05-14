package ru.pivovarov.transferservice.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pivovarov.transferservice.model.Amount;
import ru.pivovarov.transferservice.model.Transfer;
import ru.pivovarov.transferservice.model.TransferRq;
import ru.pivovarov.transferservice.repository.TransferRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@SpringBootTest
class TransferServiceImplTest {
    static TransferServiceImpl transferService;
    static TransferRepository transferRepository;
    static TransferRq transferRq;

    @BeforeAll
    static void setUp() {
        transferRepository = new TransferRepository();
        transferService = new TransferServiceImpl(transferRepository);
        transferRq = new TransferRq(
                new Amount("RUR", 500),
                "111",
                "1111111111111111",
                "02/25",
                "2222222222222222"
        );
    }

    @Test
    void doTransfer() {
        String id = transferService.doTransfer(transferRq);
        Transfer transferExpected = transferRepository.getTransfer(id);
        Assertions.assertEquals(transferExpected.amount().value(), transferRq.amount().value());
        Assertions.assertEquals(transferExpected.amount().currency(), transferRq.amount().currency());
        Assertions.assertEquals(transferExpected.cardFromCVV(), transferRq.cardFromCVV());
        Assertions.assertEquals(transferExpected.cardFromNumber(), transferRq.cardFromNumber());
        Assertions.assertEquals(transferExpected.cardFromValidTill(), transferRq.cardFromValidTill());
        Assertions.assertEquals(transferExpected.cardToNumber(), transferRq.cardToNumber());
        Assertions.assertNotNull(transferExpected.date());
        Assertions.assertNotNull(transferExpected.time());
    }
}