package com.example.inventory_mgt_backend.service;

import com.example.inventorymgtbackend.entity.Warehouse;
import com.example.inventorymgtbackend.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    public Warehouse getWarehouseById(Long id) {
        return warehouseRepository.findById(id).orElse(null);
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    // 新增更新仓库容积的方法
    public boolean updateWarehouseVolume(Long warehouseId, int volumeChange) {
        Warehouse warehouse = getWarehouseById(warehouseId);
        if (warehouse != null && warehouse.canUpdateVolume(volumeChange)) {
            warehouse.setUsedVolume(warehouse.getUsedVolume() + volumeChange);
            saveWarehouse(warehouse);
            return true;
        }
        return false;
    }
}