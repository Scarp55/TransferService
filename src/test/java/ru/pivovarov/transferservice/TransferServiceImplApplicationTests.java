package ru.pivovarov.transferservice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import ru.pivovarov.transferservice.model.Amount;
import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.ConfirmRs;
import ru.pivovarov.transferservice.model.TransferRq;
import ru.pivovarov.transferservice.repository.TransferRepository;
import ru.pivovarov.transferservice.service.TransferServiceImpl;

@SpringBootTest
class TransferServiceImplApplicationTests {

    static TransferServiceImpl transferServiceImpl;
    static TransferRepository transferRepository;

    @BeforeAll
    static void setUp() {
        transferRepository = new TransferRepository();
        transferServiceImpl = new TransferServiceImpl(transferRepository);
    }

    @Test
    void serviceDoTransferTest() {

        TransferRq transferRq = new TransferRq(
                new Amount("RUR", 500),
                "345",
                "0000111122223333",
                "12/24",
                "0000111122224444"
        );

        String id = transferServiceImpl.doTransfer(transferRq);

        Assertions.assertEquals(id, "1");
    }

    @Test
    void serviceConfirmOperationTest() {
        ConfirmRq confirmRq = Mockito.spy(ConfirmRq.class);
        Mockito.when(confirmRq.operationId()).thenReturn("1");
        Mockito.when(confirmRq.code()).thenReturn("0000");

        ConfirmRs confirmRs = new ConfirmRs(transferServiceImpl.confirmOperation(confirmRq));

        Assertions.assertEquals(confirmRs.operationId(), "1");
    }

}