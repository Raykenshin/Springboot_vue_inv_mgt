package com.example.inventorymgtbackend.repository;

import com.example.inventorymgtbackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductNumber(String productNumber);
    Product findByNationalStandardNumber(String nationalStandardNumber);
}