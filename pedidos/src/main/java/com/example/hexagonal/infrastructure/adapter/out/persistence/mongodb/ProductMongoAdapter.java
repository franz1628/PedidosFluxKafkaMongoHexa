package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

import com.example.hexagonal.application.port.out.ProductRepositoryPort;
import com.example.hexagonal.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class ProductMongoAdapter implements ProductRepositoryPort {

    private final MongoProductRepository mongoProductRepository;

    public ProductMongoAdapter(MongoProductRepository mongoProductRepository) {
        this.mongoProductRepository = mongoProductRepository;
    }

    @Override
    public Mono<Product> save(Product product) {
        ProductEntity entity = ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();

        return mongoProductRepository.save(entity)
                .map(savedEntity -> new Product(savedEntity.getId(), savedEntity.getName(), savedEntity.getPrice()));
    }

    @Override
    public Mono<Product> findById(String id) {
        return mongoProductRepository.findById(id)
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice()));
    }

    @Override
    public Flux<Product> findAll() {
        return mongoProductRepository.findAll()
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice()));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoProductRepository.deleteById(id);
    }
}
