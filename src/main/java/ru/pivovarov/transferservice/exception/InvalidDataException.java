package ru.pivovarov.transferservice.exception;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String msg) {
        super(msg);
    }
}
