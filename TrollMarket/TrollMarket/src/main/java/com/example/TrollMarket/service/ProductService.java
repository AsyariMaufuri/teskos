package com.example.TrollMarket.service;

import com.example.TrollMarket.dto.product.ProductDTO;
import com.example.TrollMarket.dto.product.ProductUpdateInsert;
import com.example.TrollMarket.dto.shipper.ShipperDTO;
import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProduct(Integer page,String username);
    List<ProductDTO> getAllProductByNameCategoryDescription(String productName,String category,String description,Integer page);
    ProductUpdateInsert getProductByName(Integer productId);
    Integer getTotal(String username);
    Integer getTotalByNameCategoryDescription(String productName,String category,String description);
    Integer totalPage(String username);

    void delete(Integer productId);
    void discontinue(Integer productId);
    void updateInsert(ProductUpdateInsert productUpdateInsert);
}
