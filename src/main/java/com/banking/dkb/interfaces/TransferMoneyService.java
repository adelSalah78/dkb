package com.banking.dkb.interfaces;

import com.banking.dkb.entities.Account;
import com.banking.dkb.entities.Transaction;
import com.banking.dkb.enums.TransactionStatus;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.repositories.AccountRepository;
import com.banking.dkb.repositories.TransactionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Optional;

@Data
public abstract class TransferMoneyService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public void depositMoneyIBAN(String iban, Long amount) {
        Optional<Account> accountOptional = accountRepository.findById(iban);
        if(!accountOptional.isPresent()){
            throw new BusinessException(Utilities.noAccountFound, HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();
        if(account.isLocked()){
            throw new BusinessException(Utilities.accountIsLocked,HttpStatus.BAD_REQUEST);
        }
        account.setBalance(account.getBalance()+amount);
        if(account.getBalance()<0){
            throw new BusinessException(Utilities.notSufficientAmount,HttpStatus.BAD_REQUEST);
        }
        accountRepository.save(account);
    }

    public void depositMoneyAccount(Account account,Long amount) {
        if(account.isLocked()){
            throw new BusinessException(Utilities.accountIsLocked,HttpStatus.BAD_REQUEST);
        }
        account.setBalance(account.getBalance()+amount);
        if(account.getBalance()<0){
            throw new BusinessException(Utilities.notSufficientAmount,HttpStatus.BAD_REQUEST);
        }
        accountRepository.save(account);
    }

    public abstract void transferMoney(String sourceIBAN,String destinationIBAN,Long amount);
}
