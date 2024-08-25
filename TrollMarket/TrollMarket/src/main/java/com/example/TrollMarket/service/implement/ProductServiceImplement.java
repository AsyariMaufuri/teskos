package com.example.TrollMarket.service.implement;

import com.example.TrollMarket.dto.product.ProductDTO;
import com.example.TrollMarket.dto.product.ProductUpdateInsert;
import com.example.TrollMarket.dto.shipper.ShipperDTO;
import com.example.TrollMarket.dto.shipper.ShipperUpdateInsert;
import com.example.TrollMarket.entitiy.Product;
import com.example.TrollMarket.entitiy.Shipper;
import com.example.TrollMarket.helper.Helper;
import com.example.TrollMarket.repository.ProductRepository;
import com.example.TrollMarket.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProductServiceImplement implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImplement(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<ProductDTO> getAllProduct(Integer page,String username) {
        Pageable pageable = PageRequest.of(page-1, 5, Sort.by("productId"));
        List<Product> products = repository.getAllProduct(pageable,username);
        List<ProductDTO> gridProduct = new LinkedList<>();
        for (var pro: products){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(pro.getProductId());
            productDTO.setProductName(pro.getProductName());
            productDTO.setCategory(pro.getCategory());
            productDTO.setDiscontinue(pro.getDiscontinue());
            gridProduct.add(productDTO);
        }
        return gridProduct;
    }

    @Override
    public List<ProductDTO> getAllProductByNameCategoryDescription(String productName, String category, String description, Integer page) {
        Pageable pageable = PageRequest.of(page-1, 5, Sort.by("productId"));
        List<Product> products = repository.getAllProductbyNameCategoryDescription(productName,category,description,pageable);
        List<ProductDTO> gridProduct = new LinkedList<>();
        for (var pro: products){
            ProductDTO productDTO = new ProductDTO();
            productDTO.setProductId(pro.getProductId());
            productDTO.setProductName(pro.getProductName());
            productDTO.setCategory(pro.getCategory());
            productDTO.setDiscontinue(pro.getDiscontinue());
            productDTO.setPriceIDR(Helper.FormatIDR(pro.getPrice()));
            gridProduct.add(productDTO);
        }
        return gridProduct;
    }

    @Override
    public ProductUpdateInsert getProductByName(Integer productId) {
        ProductUpdateInsert productUpdateInsert = new ProductUpdateInsert();
        productId = productId == null ? 0 : productId;
        if (productId != 0 ){
            Product pro = repository.getProductById(productId);
            productUpdateInsert.setProductId(pro.getProductId());
            productUpdateInsert.setProductName(pro.getProductName());
            productUpdateInsert.setCategory(pro.getCategory());
            productUpdateInsert.setPrice(pro.getPrice());
            productUpdateInsert.setDescription(pro.getDescription());
            productUpdateInsert.setDiscontinue(pro.getDiscontinue());
            productUpdateInsert.setUsername(pro.getUsername());

        }
        return productUpdateInsert;
    }

    @Override
    public Integer getTotal(String username) {
        return repository.getTotal(username);
    }

    @Override
    public Integer getTotalByNameCategoryDescription(String productName, String category, String description) {
        return repository.getTotalbyNameCategoryDescription(productName,category,description);
    }

    @Override
    public Integer totalPage(String username){
        int total = repository.getTotal(username);
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
    public void delete(Integer productId) {
        Product product = repository.getProductById(productId);
        repository.delete(product);

    }

    @Override
    public void discontinue(Integer productId) {
        Product product = repository.getProductById(productId);
        product.setDiscontinue(false);
        repository.save(product);
    }

    @Override
    public void updateInsert(ProductUpdateInsert productUpdateInsert) {
        Product product = new Product();
        product.setProductId(productUpdateInsert.getProductId());
        product.setProductName(productUpdateInsert.getProductName());
        product.setCategory(productUpdateInsert.getCategory());
        product.setPrice(productUpdateInsert.getPrice());
        product.setDescription(productUpdateInsert.getDescription());
        product.setDiscontinue(productUpdateInsert.getDiscontinue());
        product.setUsername(productUpdateInsert.getUsername());
        repository.save(product);
    }
}
