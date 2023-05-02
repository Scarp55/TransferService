package ru.pivovarov.transferservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pivovarov.transferservice.exception.ErrorTransferOrConfirmException;
import ru.pivovarov.transferservice.model.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class ErrorTransferOrConfirmExceptionHandler {

    @ExceptionHandler(ErrorTransferOrConfirmException.class)
    public ResponseEntity<ErrorResponse> handler(ErrorTransferOrConfirmException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse("INTERNAL_SERVER_ERROR : " + e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
