package com.example.hexagonal.application.port.in;

import com.example.hexagonal.domain.model.Product;
import reactor.core.publisher.Mono;

public interface CreateProductUseCase {
    Mono<Product> createProduct(Product product);
}
