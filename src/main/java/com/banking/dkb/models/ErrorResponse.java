package com.banking.dkb.models;

import com.banking.dkb.interfaces.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorResponse implements Response {
    String message;
    HttpStatus httpStatus;
}
