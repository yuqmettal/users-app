package com.nisum.users.infrastructure.api.controller;

import com.nisum.users.domain.exceptions.EmailAlreadyExistsException;
import com.nisum.users.infrastructure.api.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje("El correo ya registrado");
        return ResponseEntity.badRequest().body(error);
    }
}
