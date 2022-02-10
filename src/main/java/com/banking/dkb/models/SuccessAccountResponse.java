package com.banking.dkb.models;

import com.banking.dkb.entities.Account;
import com.banking.dkb.interfaces.Response;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessAccountResponse implements Response {
    Account account_details;
}
