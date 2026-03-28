package com.example.hexagonal.domain.service;

import com.example.hexagonal.application.port.in.CreateProductUseCase;
import com.example.hexagonal.application.port.out.ProductRepositoryPort;
import com.example.hexagonal.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService implements CreateProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    public ProductService(ProductRepositoryPort productRepositoryPort) {
        this.productRepositoryPort = productRepositoryPort;
    }

    @Override
    public Mono<Product> createProduct(Product product) {
        return productRepositoryPort.save(product);
    }

    public Mono<Product> getProductById(String id) {
        return productRepositoryPort.findById(id);
    }

    public Flux<Product> getAllProducts() {
        return productRepositoryPort.findAll();
    }
}
