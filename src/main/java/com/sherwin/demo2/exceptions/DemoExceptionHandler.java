package com.sherwin.demo2.exceptions;
import com.sherwin.demo2.infrastructure.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class DemoExceptionHandler {
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorDetail> handleNumberFormatException(NumberFormatException ex, WebRequest request) {
        ErrorDetail errDetail = new ErrorDetail(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<ErrorDetail>(errDetail, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorDetail> NoSuchElementException(NoSuchElementException ex)
    {
        ErrorDetail errDetail = new ErrorDetail(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                "check that the labor you are trying to update exists in the table"
        );
        return new ResponseEntity<ErrorDetail>(errDetail, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Map<String, String> handleConstraintViolation(MethodArgumentNotValidException ex, WebRequest request){
        Map<String,String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(),error.getDefaultMessage());
        });
        //look in sets of errorDetail
        return errorMap;
    }
}