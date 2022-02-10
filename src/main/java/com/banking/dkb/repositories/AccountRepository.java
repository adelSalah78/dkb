package com.banking.dkb.repositories;

import com.banking.dkb.entities.Account;
import com.banking.dkb.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    @Query("select a from Account a where a.accountType in :accountTypes")
    List<Account> findAllByAccountTypes(List<AccountType> accountTypes);
}
