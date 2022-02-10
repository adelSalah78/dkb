package com.banking.dkb.entities;

import com.banking.dkb.enums.AccountType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name ="ACCOUNT")
@Data
public class Account {
    @Id
    @Column(name = "IBAN")
    private String iban;
    @Column(name = "ACCOUNT_TYPE")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;
    @Column(name = "BALANCE")
    private long balance;
    @Column(name = "LOCKED")
    private boolean locked;
}
