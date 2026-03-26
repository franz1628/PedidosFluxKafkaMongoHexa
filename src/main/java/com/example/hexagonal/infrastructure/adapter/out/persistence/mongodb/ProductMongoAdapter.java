package com.example.hexagonal.infrastructure.adapter.out.persistence.mongodb;

import com.example.hexagonal.application.port.out.ProductRepositoryPort;
import com.example.hexagonal.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Primary
@RequiredArgsConstructor
public class ProductMongoAdapter implements ProductRepositoryPort {

    private final MongoProductRepository mongoProductRepository;

    @Override
    public Product save(Product product) {
        ProductEntity entity = ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
        
        ProductEntity savedEntity = mongoProductRepository.save(entity);
        
        return new Product(savedEntity.getId(), savedEntity.getName(), savedEntity.getPrice());
    }

    @Override
    public Optional<Product> findById(String id) {
        return mongoProductRepository.findById(id)
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice()));
    }

    @Override
    public List<Product> findAll() {
        return mongoProductRepository.findAll().stream()
                .map(entity -> new Product(entity.getId(), entity.getName(), entity.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        mongoProductRepository.deleteById(id);
    }
}
