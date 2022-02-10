package com.banking.dkb.repositories;

import com.banking.dkb.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query("select t from Transaction t where t.sourceIBAN = :iban or t.destinationIBAN = :iban")
    List<Transaction> findAllAccountTransactionHistory(String iban);
}
