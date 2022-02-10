package com.banking.dkb.infrastructure.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    String message;
    HttpStatus httpStatus;
}
