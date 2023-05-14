package ru.pivovarov.transferservice.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pivovarov.transferservice.model.Transfer;

@SpringBootTest
class TransferRepositoryTest {
    static TransferRepository transferRepository = new TransferRepository();

    static Transfer transfer;
    static Transfer transferActual;
    static int id = 0;

    @BeforeAll
    static void setUp() {
        for (int i = 1; i < 4; i++) {
            transfer = Mockito.mock(Transfer.class);
            if (i == 2) {
                transferActual = transfer;
            }
            transferRepository.addTransfer(transfer);
        }
    }

    @Test
    void addTransfer() {
        id = transferRepository.addTransfer(Mockito.mock(Transfer.class));
        Assertions.assertEquals(id, 4);
    }

    @Test
    void getTransfer() {
        Transfer transferExpected = transferRepository.getTransfer("2");
        Assertions.assertEquals(transferExpected.hashCode(), transferActual.hashCode());
    }
}