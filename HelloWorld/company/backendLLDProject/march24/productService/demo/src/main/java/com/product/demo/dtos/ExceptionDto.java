package com.product.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ExceptionDto {
    private String message;
    private HttpStatus httpStatus;

    public ExceptionDto(HttpStatus httpStatus, String message){
        this.httpStatus= httpStatus;
        this.message = message;
    }
}
