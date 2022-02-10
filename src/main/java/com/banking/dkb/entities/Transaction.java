package com.banking.dkb.entities;

import com.banking.dkb.enums.TransactionStatus;
import com.banking.dkb.enums.TransactionType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name ="TRANSACTION")
@Data
public class Transaction  {
    @Id
    @Column(name = "TRANSACTION_UUID")
    private String uuid;
    @Column(name = "SOURCE_IBAN")
    private String sourceIBAN;
    @Column(name = "DESTINATION_IBAN")
    private String destinationIBAN;
    @Column(name = "TRANSACTION_STATUS")
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "AMOUNT")
    private Long amount;

    @Column(name = "TRANSACTION_TYPE")
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "CREATION_DATE")
    private Date creationDate = new Date();
}
