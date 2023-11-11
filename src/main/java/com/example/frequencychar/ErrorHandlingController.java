package com.example.frequencychar;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlingController {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> onConstraintException(ConstraintViolationException e) {
        return new ResponseEntity<>(new ErrorMessage(HttpStatus.BAD_REQUEST.value(),
                "Длина строки превышает максимально допустимое значение"),
                HttpStatus.BAD_REQUEST);
    }
}
