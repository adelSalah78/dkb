package com.banking.dkb.infrastructure;

import com.banking.dkb.entities.Transaction;
import com.banking.dkb.enums.TransactionStatus;
import com.fasterxml.uuid.Generators;

import java.util.Date;
import java.util.UUID;

public class Utilities {

    public static final String accountNotFound = "Account Not Found!";
    public static final String noInfoFound = "Your search has no results!";
    public static final String noAccountFound = "Account not found!";

    public static final String savingsToCheckingTransferError = "You Can only transfer money from saving account to checking account!";
    public static final String privateLoanTransferError = "Can't withdraw money from private loan account!";

    public static final String invalidAccountType = "Invalid account type!";

    public static final String accountLocked = "Account locked successfully!";
    public static final String accountUnLocked = "Account unlocked successfully!";

    public static final String moneyDeposited = "Money Deposited Successfully!";
    public static final String accountIsLocked = "Account is locked!";

    public static final String notSufficientAmount = "Your account hasn't sufficient amount for this operation!";

    public static final String contactAdmin = "Please contact administrator!";

    public static final String moneyTransferred = "%s transferred from %s to %s";

    public static String generateIBAN_TransactionUUID() {
//        UUID uuid= UUID.fromString(new Date().toString());
        UUID uuid = Generators.timeBasedGenerator().generate();
        return uuid.toString();
    }

}
