package com.banking.dkb.infrastructure.factories;

import com.banking.dkb.entities.Account;
import com.banking.dkb.enums.AccountType;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.interfaces.TransferMoneyService;
import com.banking.dkb.services.money_transfers.GeneralTransferMoneyService;
import com.banking.dkb.services.money_transfers.PrivateLoanTransferMoneyService;
import com.banking.dkb.services.money_transfers.SavingsTransferMoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransferMoneyServiceFactory {
    @Autowired
    GeneralTransferMoneyService generalTransferMoneyService;

    @Autowired
    PrivateLoanTransferMoneyService privateLoanTransferMoneyService;

    @Autowired
    SavingsTransferMoneyService savingsTransferMoneyService;


    public TransferMoneyService createTransferMoneyService(Account source) {
        if(source.getAccountType().equals(AccountType.CHECKING))
            return generalTransferMoneyService;
        else if(source.getAccountType().equals(AccountType.PRIVATE_LOAN))
            return privateLoanTransferMoneyService;
        else if(source.getAccountType().equals(AccountType.SAVINGS))
            return savingsTransferMoneyService;
        else
            throw new BusinessException(Utilities.invalidAccountType, HttpStatus.BAD_REQUEST);
    }
}
