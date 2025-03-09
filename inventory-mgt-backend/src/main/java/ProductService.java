package com.example.inventorymgtbackend.service;

import com.example.inventorymgtbackend.entity.Product;
import com.example.inventorymgtbackend.entity.Warehouse;
import com.example.inventorymgtbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsByProductNumber(String productNumber) {
        return productRepository.findByProductNumber(productNumber);
    }

    public Product getProductByNationalStandardNumber(String nationalStandardNumber) {
        return productRepository.findByNationalStandardNumber(nationalStandardNumber);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void updateProductQuantity(Long productId, int quantityChange) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            product.setQuantity(product.getQuantity() + quantityChange);
            productRepository.save(product);

            // 更新仓库使用容积
            Warehouse warehouse = product.getWarehouse();
            warehouse.setUsedVolume(warehouse.getUsedVolume() + quantityChange);
            // 这里可以添加更多的容积校验逻辑
        }
    }
}