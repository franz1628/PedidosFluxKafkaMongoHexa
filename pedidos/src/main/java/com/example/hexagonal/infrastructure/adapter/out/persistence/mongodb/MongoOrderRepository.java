package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoOrderRepository extends ReactiveMongoRepository<OrderEntity, String> {
}
