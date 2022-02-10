package com.banking.dkb.controllers;

import com.banking.dkb.entities.Account;
import com.banking.dkb.enums.TransactionStatus;
import com.banking.dkb.enums.TransactionType;
import com.banking.dkb.infrastructure.TransactionLogger;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.infrastructure.factories.TransferMoneyServiceFactory;
import com.banking.dkb.models.ErrorResponse;
import com.banking.dkb.services.AccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account/transfer-money")
public class TransferMoneyController {

    @Autowired
    TransferMoneyServiceFactory transferMoneyServiceFactory;

    @Autowired
    AccountInfoService accountInfoService;

    @Autowired
    TransactionLogger transactionLogger;

    @PostMapping("/{source}/{destination}/{amount}")
    public ResponseEntity transferMoneyFromTo(@PathVariable String source,@PathVariable String destination,@PathVariable Long amount) {
        try{
            Account account = accountInfoService.findAccountByIBAN(source);
            transferMoneyServiceFactory.createTransferMoneyService(account).transferMoney(source,destination,amount);
            transactionLogger.logTransaction(source,destination, TransactionStatus.SUCCESS, TransactionType.TRANSFER
                    ,String.format(Utilities.moneyTransferred,amount,source,destination),amount);
            return new ResponseEntity(String.format(Utilities.moneyTransferred,amount,source,destination),HttpStatus.OK);
        }
        catch(BusinessException e) {
            transactionLogger.logTransaction(source,destination, TransactionStatus.FAIL, TransactionType.DEPOSIT
                    ,e.getMessage(),amount);
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            transactionLogger.logTransaction(source,destination, TransactionStatus.FAIL, TransactionType.DEPOSIT
                    ,Utilities.contactAdmin,amount);
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PostMapping("/deposit/{iban}/{amount}")
    public ResponseEntity depositMoney(@PathVariable String iban, @PathVariable Long amount) {
        try {
            Account account = accountInfoService.findAccountByIBAN(iban);
            transferMoneyServiceFactory.createTransferMoneyService(account).depositMoneyAccount(account, amount);
            transactionLogger.logTransaction(iban,null, TransactionStatus.SUCCESS, TransactionType.DEPOSIT
                    ,Utilities.moneyDeposited,amount);
            return new ResponseEntity(Utilities.moneyDeposited,HttpStatus.OK);
        }
        catch(BusinessException e) {
            transactionLogger.logTransaction(iban,null, TransactionStatus.FAIL, TransactionType.DEPOSIT
                    ,e.getMessage(),amount);
            return new ResponseEntity<>(new ErrorResponse(e.getMessage(),e.getHttpStatus()),e.getHttpStatus());
        }
        catch(Exception e){
            transactionLogger.logTransaction(iban,null, TransactionStatus.FAIL, TransactionType.DEPOSIT
                    ,Utilities.contactAdmin,amount);
            return new ResponseEntity<>(new ErrorResponse(Utilities.contactAdmin, HttpStatus.EXPECTATION_FAILED),HttpStatus.EXPECTATION_FAILED);
        }
    }
}
