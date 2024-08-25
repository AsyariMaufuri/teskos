package com.example.TrollMarket.service;

import com.example.TrollMarket.dto.ShipperOptionDTO;
import com.example.TrollMarket.dto.shipper.ShipperDTO;
import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;

import java.util.List;

public interface ShipperService {
    List<ShipperDTO> getAllShipper(Integer page);
    List<ShipperOptionDTO> getAllShipperServiceOn();
    ShipperUpdateInsert getShipperByName(String shipperName);
    Integer getTotal();
    Integer totalPage();

    void delete(String shipperName);
    void serviceStop(String shipperName);
    void updateInsert(ShipperUpdateInsert shipperUpdateInsert);

}
