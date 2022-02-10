package com.banking.dkb.infrastructure;

import com.banking.dkb.entities.Transaction;
import com.banking.dkb.enums.TransactionStatus;
import com.banking.dkb.enums.TransactionType;
import com.banking.dkb.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionLogger {

    @Autowired
    TransactionRepository transactionRepository;
    public void logTransaction(String source, String dest, TransactionStatus status
            , TransactionType type, String message, long amount) {
        Thread thread = new Thread(() -> {
            Transaction transaction = new Transaction();
            transaction.setTransactionStatus(status);
            transaction.setCreationDate(new Date());
            transaction.setSourceIBAN(source);
            transaction.setDestinationIBAN(dest);
            transaction.setUuid(Utilities.generateIBAN_TransactionUUID());
            transaction.setTransactionType(type);
            transaction.setAmount(amount);
            transaction.setMessage(message);
            transactionRepository.save(transaction);
        });
        thread.start();
    }
}
