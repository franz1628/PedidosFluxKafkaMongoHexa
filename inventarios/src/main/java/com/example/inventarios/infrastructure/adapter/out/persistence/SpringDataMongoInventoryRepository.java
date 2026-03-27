package com.example.inventarios.infrastructure.adapter.out.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface SpringDataMongoInventoryRepository extends MongoRepository<InventoryEntity, String> {
    Optional<InventoryEntity> findByProductId(String productId);
}
