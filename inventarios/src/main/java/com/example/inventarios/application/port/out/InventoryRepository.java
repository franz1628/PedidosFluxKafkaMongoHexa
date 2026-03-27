package com.example.inventarios.application.port.out;

import com.example.inventarios.domain.model.InventoryItem;
import java.util.Optional;

public interface InventoryRepository {
    Optional<InventoryItem> findByProductId(String productId);
    void save(InventoryItem inventoryItem);
}
