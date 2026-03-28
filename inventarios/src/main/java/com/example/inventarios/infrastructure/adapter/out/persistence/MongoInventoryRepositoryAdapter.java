package com.example.inventarios.infrastructure.adapter.out.persistence;

import com.example.inventarios.application.port.out.InventoryRepository;
import com.example.inventarios.domain.model.InventoryItem;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MongoInventoryRepositoryAdapter implements InventoryRepository {

    private final SpringDataMongoInventoryRepository repository;

    public MongoInventoryRepositoryAdapter(SpringDataMongoInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<InventoryItem> findByProductId(String productId) {
        return repository.findByProductId(productId)
                .map(entity -> new InventoryItem(entity.getId(), entity.getProductId(), entity.getQuantity()));
    }

    @Override
    public Mono<InventoryItem> save(InventoryItem inventoryItem) {
        InventoryEntity entity = new InventoryEntity(
                inventoryItem.getId(),
                inventoryItem.getProductId(),
                inventoryItem.getQuantity()
        );
        return repository.save(entity)
                .map(savedEntity -> new InventoryItem(savedEntity.getId(), savedEntity.getProductId(), savedEntity.getQuantity()));
    }
}
