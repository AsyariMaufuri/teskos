package com.example.TrollMarket.repository;

import com.example.TrollMarket.entitiy.Shipper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ShipperRepository extends JpaRepository<Shipper, String> {
    @Query(value = """
            SELECT ship
            FROM Shipper ship
            """)
    List<Shipper> getAllShipper(Pageable pageable);
    @Query(value = """
            SELECT ship
            FROM Shipper ship
            WHERE ship.service = true
            """)
    List<Shipper> getAllShipperServiceOn();

    @Query(value = """
        SELECT ship
        FROM Shipper ship
        WHERE ship.shipperName = :shipperName
        """)
    Shipper getShipperByName(String shipperName);


    @Query(value = """
            SELECT count(*)
            FROM Shipper ship
            """)
    Integer getTotal();


}
