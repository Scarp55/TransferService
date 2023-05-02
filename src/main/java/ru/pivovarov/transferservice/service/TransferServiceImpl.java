package ru.pivovarov.transferservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ru.pivovarov.transferservice.exception.ErrorTransferOrConfirmException;
import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.Transfer;
import ru.pivovarov.transferservice.model.TransferRq;
import ru.pivovarov.transferservice.repository.TransferRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;


    @Override
    public String doTransfer(TransferRq transferRq) {
        Transfer transfer = new Transfer();
        BeanUtils.copyProperties(transferRq, transfer);
        transfer.setDate(createDate());
        transfer.setTime(createTime());
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
        Transfer transfer = transferRepository.getTransfer(confirmRq.getOperationId());
        if (confirmRq.getCode().equals("0000")) {
            if (transfer == null) {
                throw new ErrorTransferOrConfirmException("Error confirm : " + confirmRq);
            }
            log.info("Перевод осуществлен: " + transfer);
        } else {
            throw new ErrorTransferOrConfirmException("Error confirm Code : " + confirmRq);
        }
        return confirmRq.getOperationId();
    }

}
