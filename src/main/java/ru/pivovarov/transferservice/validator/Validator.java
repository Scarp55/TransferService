package ru.pivovarov.transferservice.validator;

import org.springframework.stereotype.Component;
import ru.pivovarov.transferservice.exception.ErrorException;
import ru.pivovarov.transferservice.model.ConfirmRq;
import ru.pivovarov.transferservice.model.TransferRq;

@Component
public class Validator {
    public void checkValid(TransferRq transfer) {
        StringBuilder msg = new StringBuilder();
        boolean flag = true;
        if (transfer.cardFromCVV() == null || transfer.cardFromCVV().length() == 0) {
            msg.append("/Invalid CardFromCVV ");
            flag = false;
        }
        if (transfer.cardFromNumber() == null || transfer.cardFromNumber().length() == 0) {
            msg.append("/Invalid CardFromNumber ");
            flag = false;
        }
        if (transfer.cardFromValidTill() == null || transfer.cardFromValidTill().length() == 0) {
            msg.append("/Invalid CardFromValidTill ");
            flag = false;
        }
        if (transfer.cardToNumber() == null || transfer.cardToNumber().length() == 0) {
            msg.append("/Invalid CardToNumber ");
            flag = false;
        }

        if (transfer.amount() == null || transfer.amount().value() < 0 || transfer.amount().currency() == null) {
            msg.append("/Invalid Amount ");
            flag = false;
        }

        if (!flag) {
            throw new ErrorException("Invalid transfer :" + msg);
        }
    }

    public void checkValid(ConfirmRq info) {
        StringBuilder msg = new StringBuilder();
        boolean flag = true;

        if (info.code() == null || info.code().length() == 0) {
            msg.append("/Invalid code");
            flag = false;
        }

        if (info.operationId() == null || info.operationId().length() == 0 || isNumeric(info.operationId())) {
            msg.append("/Invalid OperationId ");
            flag = false;
        }

        if (!flag) {
            throw new ErrorException("Invalid confirm :" + msg);
        }
    }

    private boolean isNumeric(String strNum) {
        boolean flag = true;
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException e) {
            flag = false;
        }
        return flag;
    }
}
