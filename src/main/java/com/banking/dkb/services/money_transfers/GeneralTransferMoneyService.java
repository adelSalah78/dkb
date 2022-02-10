package com.banking.dkb.services.money_transfers;

import com.banking.dkb.interfaces.TransferMoneyService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GeneralTransferMoneyService extends TransferMoneyService {

    @Override
    @Transactional
    public void transferMoney(String sourceIBAN, String destinationIBAN,Long amount) {
        this.depositMoneyIBAN(sourceIBAN,-amount);
        this.depositMoneyIBAN(destinationIBAN,amount);
    }
}
