package com.banking.dkb.services;

import com.banking.dkb.entities.Account;
import com.banking.dkb.entities.Transaction;
import com.banking.dkb.enums.AccountType;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.repositories.AccountRepository;
import com.banking.dkb.repositories.TransactionRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class AccountInfoService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public Account findAccountByIBAN(String iban) {
        Optional<Account> accountOptional = accountRepository.findById(iban);
        if(!accountOptional.isPresent()){
            throw new BusinessException(Utilities.accountNotFound, HttpStatus.NOT_FOUND);
        }
        return accountOptional.get();
    }

    public Long showBalance(String iban) {
        Optional<Account> accountOptional = accountRepository.findById(iban);
        if(!accountOptional.isPresent()){
            throw new BusinessException(Utilities.accountNotFound, HttpStatus.NOT_FOUND);
        }
        return accountOptional.get().getBalance();
    }

    public List<Account> findByAccountTypes(String[] accountTypes){
        List<AccountType> accTypes = new ArrayList<>();

        Arrays.stream(accountTypes).forEach(acc -> {
            if(AccountType.valueOf(acc)!=null){
                accTypes.add(AccountType.valueOf(acc));
            }
        });
        List<Account> result = accountRepository.findAllByAccountTypes(accTypes);
        if(result == null || result.size() == 0) {
            throw new BusinessException(Utilities.noInfoFound, HttpStatus.NOT_FOUND);
        }
        return result;
    }

    public List<Transaction> findTransactionHistory(String iban){
        List<Transaction> transactions = transactionRepository.findAllAccountTransactionHistory(iban);
        if(transactions == null || transactions.size() == 0) {
            throw new BusinessException(Utilities.noInfoFound, HttpStatus.NOT_FOUND);
        }
        return transactions;
    }
}
