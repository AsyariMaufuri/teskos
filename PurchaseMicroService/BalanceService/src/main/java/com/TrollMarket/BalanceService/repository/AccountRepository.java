package com.TrollMarket.BalanceService.repository;

import com.TrollMarket.BalanceService.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    @Query(value = """
        SELECT acc
        FROM Account acc
        WHERE acc.role = 'buyer'
        """)
    List<Account> getBuyerAccount();


    @Query(value = """
        SELECT acc
        FROM Account acc
        WHERE acc.role = 'seller'
        """)
    List<Account> getSellerAccount();

    @Query(value = """
        SELECT acc
        FROM Account acc
        WHERE acc.username = :username
        """)
    Account getAccountByUsername(String username);
}
