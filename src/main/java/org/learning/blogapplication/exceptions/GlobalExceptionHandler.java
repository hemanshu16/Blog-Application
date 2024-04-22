package org.learning.blogapplication.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(Exception ex, WebRequest request) {

        ErrorMessage errorMessage = new ErrorMessage(ex.getClass().getName(), request.getDescription(false), LocalDateTime.now());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        StringBuilder errors = new StringBuilder();
        List<FieldErrorMessage> fieldErrors = new ArrayList<>();
        for (FieldError error : exception.getFieldErrors()) {
            fieldErrors.add(new FieldErrorMessage(error.getField(), error.getDefaultMessage()));
        }
        ErrorMessage errorMessage = new ErrorMessage("Total Errors:" + exception.getErrorCount(), fieldErrors.toString(), LocalDateTime.now());

        return new ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
