package com.banking.dkb.services.money_transfers;

import com.banking.dkb.entities.Account;
import com.banking.dkb.infrastructure.Utilities;
import com.banking.dkb.infrastructure.exceptions.BusinessException;
import com.banking.dkb.interfaces.TransferMoneyService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class PrivateLoanTransferMoneyService extends TransferMoneyService {

    @Override
    public void transferMoney(String sourceIBAN, String destinationIBAN, Long amount) {
        throw new BusinessException(Utilities.privateLoanTransferError, HttpStatus.BAD_REQUEST);
    }

    @Override
    public void depositMoneyIBAN(String iban, Long amount){
        if(amount<0){
            throw new BusinessException(Utilities.privateLoanTransferError, HttpStatus.BAD_REQUEST);
        }
        super.depositMoneyIBAN(iban,amount);
    }

    @Override
    public void depositMoneyAccount(Account account, Long amount){
        if(amount<0){
            throw new BusinessException(Utilities.privateLoanTransferError, HttpStatus.BAD_REQUEST);
        }
        super.depositMoneyAccount(account,amount);
    }
}
