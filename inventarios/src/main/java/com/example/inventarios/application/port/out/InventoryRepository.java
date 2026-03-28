package com.example.inventarios.application.port.out;

import com.example.inventarios.domain.model.InventoryItem;
import reactor.core.publisher.Mono;

public interface InventoryRepository {
    Mono<InventoryItem> findByProductId(String productId);
    Mono<InventoryItem> save(InventoryItem inventoryItem);
}
