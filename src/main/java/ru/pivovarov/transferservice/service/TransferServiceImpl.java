package ru.pivovarov.transferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pivovarov.transferservice.exception.ErrorException;
import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.Transfer;
import ru.pivovarov.transferservice.model.TransferRq;
import ru.pivovarov.transferservice.repository.TransferRepository;
import ru.pivovarov.transferservice.validator.Validator;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    private final Validator validator = new Validator();


    @Override
    public String doTransfer(TransferRq transferRq) {
        validator.checkValid(transferRq);
        Transfer transfer = new Transfer(
                transferRq.amount(), transferRq.cardFromCVV(),
                transferRq.cardFromNumber(), transferRq.cardFromValidTill(),
                transferRq.cardToNumber(), createDate(), createTime()
        );
        int transferId = transferRepository.addTransfer(transfer);
        log.info("Попытка перевода: " + transfer);
        return String.valueOf(transferId);
    }

    private String createDate() {
        return LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    private String createTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH.mm.ss"));
    }

    @Override
    public String confirmOperation(ConfirmRq confirmRq) {
        validator.checkValid(confirmRq);
        Transfer transfer = transferRepository.getTransfer(confirmRq.operationId());
        if (confirmRq.code().equals("0000")) {
            if (transfer == null) {
                throw new ErrorException("Error confirm : " + confirmRq);
            }
            log.info("Перевод осуществлен: " + transfer);
        } else {
            throw new ErrorException("Error confirm Code : " + confirmRq);
        }
        return confirmRq.operationId();
    }

}
