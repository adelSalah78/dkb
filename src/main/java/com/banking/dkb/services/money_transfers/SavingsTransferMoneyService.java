package com.banking.dkb.services.money_transfers;

import com.banking.dkb.entities.Account;
import com.banking.dkb.enums.AccountType;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.interfaces.TransferMoneyService;
import com.banking.dkb.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class SavingsTransferMoneyService extends TransferMoneyService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @Transactional
    public void transferMoney(String sourceIBAN, String destinationIBAN, Long amount) {
        Account destinationAccount = accountRepository.getById(destinationIBAN);
        if(!destinationAccount.getAccountType().equals(AccountType.CHECKING)){
            throw new BusinessException(Utilities.savingsToCheckingTransferError, HttpStatus.BAD_REQUEST);
        }
        this.depositMoneyIBAN(sourceIBAN,-amount);
        this.depositMoneyIBAN(destinationIBAN,amount);
    }
}
