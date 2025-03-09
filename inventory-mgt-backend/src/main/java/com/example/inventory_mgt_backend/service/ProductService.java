package com.example.inventory_mgt_backend.service;

import com.example.inventorymgtbackend.entity.Product;
import com.example.inventorymgtbackend.entity.Warehouse;
import com.example.inventorymgtbackend.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final WarehouseService warehouseService;

    public ProductService(ProductRepository productRepository, WarehouseService warehouseService) {
        this.productRepository = productRepository;
        this.warehouseService = warehouseService;
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
            Warehouse warehouse = product.getWarehouse();
            if (warehouseService.updateWarehouseVolume(warehouse.getId(), quantityChange)) {
                product.setQuantity(product.getQuantity() + quantityChange);
                productRepository.save(product);
            } else {
                // 处理容积更新失败的情况，如抛出异常或记录日志
                throw new RuntimeException("仓库容积不足，无法更新产品数量");
            }
        }
    }
}