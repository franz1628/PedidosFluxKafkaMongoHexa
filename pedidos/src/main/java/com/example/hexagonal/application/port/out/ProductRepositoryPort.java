package com.example.hexagonal.application.port.out;

import com.example.hexagonal.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepositoryPort {
    Mono<Product> save(Product product);
    Mono<Product> findById(String id);
    Flux<Product> findAll();
    Mono<Void> deleteById(String id);
}
