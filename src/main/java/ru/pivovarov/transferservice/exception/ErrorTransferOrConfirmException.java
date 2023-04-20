package ru.pivovarov.transferservice.exception;

public class ErrorTransferOrConfirmException extends RuntimeException {
    public ErrorTransferOrConfirmException(String msg) {
        super(msg);
    }
}
