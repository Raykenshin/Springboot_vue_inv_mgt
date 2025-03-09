package com.example.inventorymgtbackend.controller;

import com.example.inventorymgtbackend.entity.Product;
import com.example.inventorymgtbackend.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/productNumber/{productNumber}")
    public List<Product> getProductsByProductNumber(@PathVariable String productNumber) {
        return productService.getProductsByProductNumber(productNumber);
    }

    @GetMapping("/nationalStandardNumber/{nationalStandardNumber}")
    public Product getProductByNationalStandardNumber(@PathVariable String nationalStandardNumber) {
        return productService.getProductByNationalStandardNumber(nationalStandardNumber);
    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @PostMapping("/updateQuantity/{productId}/{quantityChange}")
    public void updateProductQuantity(@PathVariable Long productId, @PathVariable int quantityChange) {
        productService.updateProductQuantity(productId, quantityChange);
    }
}