package com.example.inventarios.infrastructure.adapter.out.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface SpringDataMongoInventoryRepository extends ReactiveMongoRepository<InventoryEntity, String> {
    Mono<InventoryEntity> findByProductId(String productId);
}
