package com.example.inventory_mgt_backend.repository;

import com.example.inventorymgtbackend.entity.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
}