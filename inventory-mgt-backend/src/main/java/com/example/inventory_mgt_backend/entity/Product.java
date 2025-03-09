package com.example.inventory_mgt_backend.entity;

import com.example.Warehouse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productNumber;
    private String name;
    private String color;
    private int quantity;
    private String nationalStandardNumber;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    // 构造函数、Getter 和 Setter 方法
    public Product() {}

    public Product(String productNumber, String name, String color, int quantity, String nationalStandardNumber, Warehouse warehouse) {
        this.productNumber = productNumber;
        this.name = name;
        this.color = color;
        this.quantity = quantity;
        this.nationalStandardNumber = nationalStandardNumber;
        this.warehouse = warehouse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNationalStandardNumber() {
        return nationalStandardNumber;
    }

    public void setNationalStandardNumber(String nationalStandardNumber) {
        this.nationalStandardNumber = nationalStandardNumber;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}