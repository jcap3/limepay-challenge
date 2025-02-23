package com.example.limepay.error.handler;

import com.example.limepay.error.LimePayChallengeErrorCodes;
import com.example.limepay.error.exception.NoDirectorsFound;
import com.example.limepay.util.ResponseConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@Slf4j
@RestControllerAdvice
public class LimePayErrorHandler {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleRuntime(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseConverter.convertError(LimePayChallengeErrorCodes.INTERNAL_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleValidationError(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseConverter.convertError(LimePayChallengeErrorCodes.INVALID_REQUESTED_THRESHOLD), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoDirectorsFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoDirectorsFound(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResponseConverter.convertError(LimePayChallengeErrorCodes.EMPTY_DIRECTORS_LIST), HttpStatus.NOT_FOUND);
    }
}
