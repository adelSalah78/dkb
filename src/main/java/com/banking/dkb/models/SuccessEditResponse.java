package com.banking.dkb.models;

import com.banking.dkb.interfaces.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessEditResponse implements Response {
    String message;
}
