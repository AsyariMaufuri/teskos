package com.TrollMarket.PurchaseService.repository;

import com.TrollMarket.PurchaseService.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = """
            SELECT pro
            FROM Product pro
            WHERE pro.username = :username
            """)
    List<Product> getAllProduct(Pageable pageable, String username);

    @Query(value = """
            SELECT pro
            FROM Product pro
            WHERE pro.productName LIKE %:productName% AND
            pro.category LIKE %:category% AND
            pro.description LIKE %:description% AND
            pro.discontinue = FALSE
            """)
    List<Product> getAllProductbyNameCategoryDescription(String productName,String category,String description, Pageable pageable);

    @Query(value = """
            SELECT pro
            FROM Product pro
        WHERE pro.productId = :productId
        """)
    Product getProductById(Integer productId);


    @Query(value = """
            SELECT count(*)
            FROM Product pro
            WHERE pro.username = :username
            """)
    Integer getTotal(String username);

    @Query(value = """
            SELECT count(*)
            FROM Product pro
            WHERE pro.productName LIKE %:productName% AND
            pro.category LIKE %:category% AND
            pro.description LIKE %:description% AND
            pro.discontinue = FALSE
            """)
    Integer getTotalbyNameCategoryDescription(String productName,String category,String description);


}
