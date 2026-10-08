package com.workintech.zoo.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ZooGlobalExceptionHandler {

    @ExceptionHandler(ZooException.class)
    public ResponseEntity<ZooErrorResponse> handleZooException(ZooException exception){
        log.error(exception.getMessage());

        ZooErrorResponse response = new ZooErrorResponse(
                exception.getHttpStatus().value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ZooErrorResponse> handleException(Exception exception){
        log.error(exception.getMessage());

        ZooErrorResponse response = new ZooErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

}
