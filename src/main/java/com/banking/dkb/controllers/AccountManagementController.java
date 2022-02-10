package com.banking.dkb.controllers;

import com.banking.dkb.entities.Account;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.interfaces.Response;
import com.banking.dkb.models.ErrorResponse;
import com.banking.dkb.models.SuccessAccountResponse;
import com.banking.dkb.models.SuccessEditResponse;
import com.banking.dkb.services.AccountManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/management")
public class AccountManagementController {

    @Autowired
    AccountManagementService accountManagementService;

    @PostMapping
    public ResponseEntity<? extends Response> addNewAccount(@RequestBody Account account) {
        try{
            SuccessAccountResponse success = new SuccessAccountResponse(accountManagementService.createAccount(account));
            return new ResponseEntity<>(success,HttpStatus.CREATED);
        }
        catch(BusinessException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{iban}/lock")
    public ResponseEntity<? extends Response> lockAccount(@PathVariable String iban) {
        try{
            lockUnLockAccount(iban,true);
            SuccessEditResponse success = new SuccessEditResponse(Utilities.accountLocked);
            return new ResponseEntity<>(success,HttpStatus.OK);
        }
        catch(BusinessException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("/{iban}/unlock")
    public ResponseEntity<? extends Response> unlockAccount(@PathVariable String iban) {
        try{
            lockUnLockAccount(iban,false);
            SuccessEditResponse success = new SuccessEditResponse(Utilities.accountUnLocked);
            return new ResponseEntity<>(success,HttpStatus.OK);
        }
        catch(BusinessException e) {
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }

    private void lockUnLockAccount(String iban,boolean lock) {
        if(lock){
            accountManagementService.lockUnlockAccount(iban,true);
        }
        else{
            accountManagementService.lockUnlockAccount(iban,false);
        }
    }
}
