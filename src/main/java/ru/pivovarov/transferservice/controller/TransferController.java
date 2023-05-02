package ru.pivovarov.transferservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.ConfirmRs;
import ru.pivovarov.transferservice.model.TransferRq;
import ru.pivovarov.transferservice.model.TransferRs;
import ru.pivovarov.transferservice.service.TransferServiceImpl;

import static ru.pivovarov.transferservice.validator.Validator.checkValid;

@RestController
@CrossOrigin(origins = "${origins.host}")
@RequiredArgsConstructor
public class TransferController {
    private final TransferServiceImpl transferServiceImpl;

    @PostMapping("transfer")
    public ResponseEntity<TransferRs> doTransfer(@RequestBody TransferRq transferRq) {
        checkValid(transferRq);
        return ResponseEntity.ok(new TransferRs(transferServiceImpl.doTransfer(transferRq)));
    }

    @PostMapping("confirmOperation")
    public ResponseEntity<ConfirmRs> confirmOperation(@RequestBody ConfirmRq confirmRq) {
        checkValid(confirmRq);
        return ResponseEntity.ok(new ConfirmRs(transferServiceImpl.confirmOperation(confirmRq)));
    }
}
