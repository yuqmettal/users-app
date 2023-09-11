package com.nisum.users.infrastructure.api.controller;

import com.nisum.users.domain.exceptions.EmailAlreadyExistsException;
import com.nisum.users.domain.exceptions.InvalidEmailFormatException;
import com.nisum.users.domain.exceptions.InvalidPasswordFormatException;
import com.nisum.users.infrastructure.api.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje("El correo ya registrado");
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidEmailFormatException.class)
    public ResponseEntity<ErrorDTO> handleInvalidEmailFormatException(InvalidEmailFormatException e) {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje("El correo no cumple con el formato requerido");
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(InvalidPasswordFormatException.class)
    public ResponseEntity<ErrorDTO> handleInvalidPasswordFormatException(InvalidPasswordFormatException e) {
        ErrorDTO error = new ErrorDTO();
        error.setMensaje("El password no cumple con el formato requerido");
        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
