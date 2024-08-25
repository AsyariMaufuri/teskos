package com.example.TrollMarket.service.implement;

import com.example.TrollMarket.dto.ShipperOptionDTO;
import com.example.TrollMarket.dto.shipper.ShipperDTO;
import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;
import com.example.TrollMarket.entitiy.Shipper;
import com.example.TrollMarket.helper.Helper;
import com.example.TrollMarket.repository.ShipperRepository;
import com.example.TrollMarket.service.ShipperService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ShipperServiceImplement implements ShipperService {
    private final ShipperRepository repository;

    public ShipperServiceImplement(ShipperRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer totalPage(){
        int total = repository.getTotal();
        int hasil = 0;
        if(total %5==0 ){
            hasil = total/5;
        }else {
            int sisa = total%5;
            hasil = (total-sisa)/5;
            hasil = hasil + 1;
        }
        return hasil;
    }

    @Override
    public List<ShipperDTO> getAllShipper(Integer page) {
        Pageable pageable = PageRequest.of(page-1, 5, Sort.by("shipperName"));
        List<Shipper> shippers = repository.getAllShipper(pageable);
        List<ShipperDTO> gridShipper = new LinkedList<>();
        for (var ship: shippers){
            ShipperDTO shipperDTO = new ShipperDTO();
            shipperDTO.setShipperName(ship.getShipperName());
            shipperDTO.setService(ship.getService());
            shipperDTO.setPrice(ship.getPrice());
            shipperDTO.setPriceIDR(Helper.FormatIDR(ship.getPrice()));
            gridShipper.add(shipperDTO);
        }
        return gridShipper;
    }

    @Override
    public List<ShipperOptionDTO> getAllShipperServiceOn() {
        List<Shipper> shippers = repository.getAllShipperServiceOn();
        List<ShipperOptionDTO> optionShipper = new LinkedList<>();
        for (var ship: shippers){
            ShipperOptionDTO shipperDTO = new ShipperOptionDTO();
            shipperDTO.setShipperName(ship.getShipperName());;
            shipperDTO.setPrice(ship.getPrice());
            optionShipper.add(shipperDTO);
        }
        return optionShipper;
    }

    @Override
    public ShipperUpdateInsert getShipperByName(String shipperName) {
        ShipperUpdateInsert shipperUpdateInsert = new ShipperUpdateInsert();
        shipperName = shipperName == null ? "" : shipperName;
        if (!shipperName.equals("") ){
            Shipper shipper = repository.getShipperByName(shipperName);
            shipperUpdateInsert.setShipperName(shipper.getShipperName());
            shipperUpdateInsert.setService(shipper.getService());
            shipperUpdateInsert.setPrice(shipper.getPrice());
        }
        return shipperUpdateInsert;
    }

    @Override
    public Integer getTotal() {
        return repository.getTotal();
    }

    @Override
    public void delete(String shipperName) {
        Shipper shipper = repository.getShipperByName(shipperName);
        repository.delete(shipper);
    }

    @Override
    public void serviceStop(String shipperName) {
        Shipper shipper = repository.getShipperByName(shipperName);
        shipper.setService(false);
        repository.save(shipper);
    }

    @Override
    public void updateInsert(ShipperUpdateInsert shipperUpdateInsert) {
        Shipper shipper = new Shipper();
        shipper.setShipperName(shipperUpdateInsert.getShipperName());
        shipper.setService(shipperUpdateInsert.getService());
        shipper.setPrice(shipperUpdateInsert.getPrice());
        repository.save(shipper);
    }
}
