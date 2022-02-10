package com.banking.dkb.controllers;

import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.models.ErrorResponse;
import com.banking.dkb.services.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/info")
public class AccountInfoController {

    @Autowired
    AccountInfoService accountInfoService;

    @GetMapping("/balance/{iban}")
    public ResponseEntity showBalance(@PathVariable String iban) {
        try{
            return new ResponseEntity (accountInfoService.showBalance(iban),HttpStatus.OK);
        }
        catch(BusinessException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/{types}") // comma separated types
    public ResponseEntity findByAccountTypes(@PathVariable String types) {
        try{
            String[] accountTypes = types.split(",");
            return new ResponseEntity<> (accountInfoService.findByAccountTypes(accountTypes),HttpStatus.OK);
        }
        catch(BusinessException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/transactions-history/{iban}")
    public ResponseEntity findAccountTransactionsHistory(@PathVariable String iban) {
        try{
            return new ResponseEntity<> (accountInfoService.findTransactionHistory(iban),HttpStatus.OK);
        }
        catch(BusinessException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
