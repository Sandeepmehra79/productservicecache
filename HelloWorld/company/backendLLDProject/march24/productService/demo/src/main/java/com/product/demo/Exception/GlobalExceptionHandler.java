package com.product.demo.Exception;

import com.product.demo.dtos.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.NOT_FOUND, notFoundException.getMessage()),
            HttpStatus.NOT_FOUND);
    }



    @ExceptionHandler(NoProductInListException.class)
    public ResponseEntity<ExceptionDto> handleNoProductInListException(NoProductInListException noProductInList){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.NOT_FOUND, noProductInList.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LimitTooLargeException.class)
    public ResponseEntity<ExceptionDto> handleLimitTooLargeException(LimitTooLargeException limitTooLargeException){
        return new ResponseEntity<ExceptionDto>(new ExceptionDto(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE , limitTooLargeException.getMessage()),
                HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
    }
}
