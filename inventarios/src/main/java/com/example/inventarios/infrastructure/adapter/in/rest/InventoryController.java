package com.example.inventarios.infrastructure.adapter.in.rest;

import com.example.inventarios.application.port.out.InventoryRepository;
import com.example.inventarios.domain.model.InventoryItem;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @GetMapping("/{productId}")
    public Mono<InventoryItem> getInventory(@PathVariable String productId) {
        return inventoryRepository.findByProductId(productId);
    }

    @PostMapping
    public Mono<InventoryItem> updateInventory(@RequestBody InventoryItem item) {
        return inventoryRepository.save(item);
    }
}
