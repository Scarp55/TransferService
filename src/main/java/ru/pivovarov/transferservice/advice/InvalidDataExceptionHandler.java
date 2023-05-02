package ru.pivovarov.transferservice.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.pivovarov.transferservice.exception.InvalidDataException;
import ru.pivovarov.transferservice.model.ErrorResponse;

@RestControllerAdvice
@Slf4j
public class InvalidDataExceptionHandler {

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ErrorResponse> handler1(InvalidDataException e) {
        log.info(e.getMessage());
        return new ResponseEntity<>(
                new ErrorResponse("BAD_REQUEST : " + e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
