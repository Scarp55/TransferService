package ru.pivovarov.transferservice.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pivovarov.transferservice.model.ConfirmInfo;
import ru.pivovarov.transferservice.model.OperationResponse;
import ru.pivovarov.transferservice.model.Transfer;
import ru.pivovarov.transferservice.repository.TransferRepository;
import ru.pivovarov.transferservice.service.TransferService;

@RestController()
@CrossOrigin(origins = "https://serp-ya.github.io/card-transfer/", allowedHeaders = "*")
public class TransferController {
    private TransferService transferService;

    public TransferController() {
        TransferRepository repository = new TransferRepository();
        this.transferService = new TransferService(repository);
    }

    @PostMapping("transfer")
    public OperationResponse doTransfer(@RequestBody Transfer transfer) {
        return transferService.doTransfer(transfer);
    }

    @PostMapping("confirmOperation")
    public OperationResponse confirmOperation(@RequestBody ConfirmInfo info) {
        return transferService.confirmOperation(info);
    }
}
