package com.banking.dkb.services;

import com.banking.dkb.entities.Account;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountManagementService {

    @Autowired
    AccountRepository accountRepository;

    public Account createAccount(Account account) {
        account.setIban(Utilities.generateIBAN_TransactionUUID());
        account.setLocked(false);
        return accountRepository.save(account);
    }

    public void lockUnlockAccount(String iban,boolean lock){
        Optional<Account> accountOptional = accountRepository.findById(iban);
        if(!accountOptional.isPresent()) {
            throw new BusinessException(Utilities.accountNotFound, HttpStatus.NOT_FOUND);
        }
        Account account = accountOptional.get();
        if(lock){
            account.setLocked(true);
        }
        else{
            account.setLocked(false);
        }
        accountRepository.save(account);
    }
}
