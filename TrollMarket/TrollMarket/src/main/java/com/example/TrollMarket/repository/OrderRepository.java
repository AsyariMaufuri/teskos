package com.example.TrollMarket.repository;

import com.example.TrollMarket.entitiy.Order;
import com.example.TrollMarket.entitiy.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = """
            SELECT count(*)
            FROM Order ord
            WHERE ord.inCart = TRUE  AND ord.username = :username
            """)
    Integer checkExistOrder(String username);

    @Query(value = """
            SELECT ord
            FROM Order ord
            WHERE ord.inCart = TRUE  AND ord.username = :username 
            """)
    Order orderInCartByInvoice(String username);
}
