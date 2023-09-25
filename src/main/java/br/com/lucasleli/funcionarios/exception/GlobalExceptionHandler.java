package br.com.lucasleli.funcionarios.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public final ResponseEntity<ErrorSchema> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorSchema response = new ErrorSchema();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setError("Bad Request");
        FieldError fieldError = ex.getBindingResult().getFieldErrors().get(0);
        response.setMessage("O campo '" + fieldError.getField() + "' " + fieldError.getDefaultMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<ErrorSchema> handleNoSuchElementException(NoSuchElementException e) {
        ErrorSchema response = new ErrorSchema();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setError("Not Found");
        response.setMessage(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<ErrorSchema> handleAllUncaughtException(Exception exception) {
        ErrorSchema response = new ErrorSchema();
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setError("Internal Server Error");
        response.setMessage(exception.getMessage());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Data
    static
    class ErrorSchema {
        private Integer status;
        private String error;
        private String message;
    }

}
