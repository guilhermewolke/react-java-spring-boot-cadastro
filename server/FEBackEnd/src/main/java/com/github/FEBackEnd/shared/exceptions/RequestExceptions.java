package com.github.FEBackEnd.shared.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorCode400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorCode404() {
        return ResponseEntity.notFound().build();
    }
}
