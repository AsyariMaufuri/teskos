package com.TrollMarket.PurchaseService.repository;

import com.TrollMarket.PurchaseService.entity.OrderDetail;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = """
        SELECT ordd
        FROM OrderDetail ordd JOIN
        ordd.order ord
        WHERE ord.username = :username and ord.inCart = true
        """)
    List<OrderDetail> getOrderDetailInCartByUsername(String username, Pageable pageable);


    @Query(value = """
        SELECT ordd
        FROM OrderDetail ordd JOIN
        ordd.order ord
        WHERE ord.username = :username and ord.inCart = true and ordd.orderDetailId = :orderDetailId and ordd.productId = :productId
        """)
    OrderDetail getOrderDetailByInvoiceUsernameProductId(String username,Integer orderDetailId,Integer productId);

    @Query(value = """
        SELECT count(*)
        FROM OrderDetail ordd JOIN
        ordd.order ord
        WHERE ord.username = :username and ord.inCart = true
        """)
    Integer getTotal(String username);

    @Query("""
            SELECT ordd
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.product pro
            WHERE
                ord.inCart = false
                AND (:sellerUsername = '' OR pro.username = :sellerUsername)
                AND (:buyerUsername = '' OR ord.username = :buyerUsername)
        """)
    List<OrderDetail> getOrderDetailsHistory(Pageable page, String buyerUsername, String sellerUsername);

    @Query("""
            SELECT count(*)
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.product pro
            WHERE
                ord.inCart = false
                AND (:sellerUsername = '' OR pro.username = :sellerUsername)
                AND (:buyerUsername = '' OR ord.username = :buyerUsername)
        """)
    Integer getTotalHistory(String buyerUsername, String sellerUsername);


    @Query("""
            SELECT ordd
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.product pro
            WHERE
                ord.inCart = false
                AND (ord.username = :username OR pro.username= :username)
        """)
    List<OrderDetail> getOrderDetailsProfile(Pageable page, String username);

    @Query("""
            SELECT count(*)
            FROM OrderDetail ordd
            JOIN ordd.order ord
            JOIN ordd.product pro
            WHERE
                ord.inCart = false
                AND (ord.username = :username OR pro.username= :username)
        """)
    Integer getTotalProfile(String username);
}
