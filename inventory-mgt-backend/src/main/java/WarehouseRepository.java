package com.example.inventorymgtbackend.repository;

import com.example.inventorymgtbackend.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}