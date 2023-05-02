package ru.pivovarov.transferservice.service;

import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.TransferRq;

public interface TransferService {
    String doTransfer(TransferRq transferRq);

    String confirmOperation(ConfirmRq confirmRq);
}
