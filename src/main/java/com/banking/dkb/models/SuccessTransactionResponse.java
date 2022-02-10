package com.banking.dkb.models;

import com.banking.dkb.entities.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SuccessTransactionResponse {
    Transaction trans_details;
}
