package com.sherwin.demo2.rest;

import com.sherwin.demo2.domain.ErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class DemoExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDetail> handleNumberFormatException(NumberFormatException ex, WebRequest request)
    {
        ErrorDetail errDetail = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorDetail>(errDetail, HttpStatus.BAD_REQUEST);
    }

    //@Override
    protected ResponseEntity<Object>handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                    HttpHeaders headers, HttpStatus status, WebRequest request)
    {
        //take all errors, and create new field in new object
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        logger.error(errors);
        //should be sending error back instead of null
        return new ResponseEntity<Object>(null,HttpStatus.BAD_REQUEST);
    }

}
