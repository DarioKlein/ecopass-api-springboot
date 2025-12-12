package br.com.darioklein.ecopass.exception;

import br.com.darioklein.ecopass.service.exception.DataIntegrityViolationException;
import br.com.darioklein.ecopass.service.exception.InvalidDateFormatException;
import br.com.darioklein.ecopass.service.exception.ObjectNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class ResourceExceptionHandler {

    private ResponseEntity<StandardError> buildError(HttpStatus status, String message, List<String> errors, HttpServletRequest request) {

        StandardError err = new StandardError(LocalDateTime.now(), status.value(), message, errors, request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, e.getMessage(), null, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityViolationException e, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), null, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> errorMessages = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).toList();
        return buildError(HttpStatus.BAD_REQUEST, null, errorMessages, request);
    }

    @ExceptionHandler(InvalidDateFormatException.class)
    public ResponseEntity<StandardError> invalidDateFormat(InvalidDateFormatException e, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), null, request);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<StandardError> invalidFormat(InvalidFormatException e, HttpServletRequest request) {

        String customMessage = String.format("Formato de dado inválido para o campo '%s'. O valor '%s' não pode ser convertido para o tipo '%s'.", e.getPath().getLast().getFieldName(), e.getTargetType().getSimpleName(), e.getValue());

        return buildError(HttpStatus.BAD_REQUEST, customMessage, null, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardError> handleEnumError(IllegalArgumentException e, HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, e.getMessage(), null, request);
    }
}
