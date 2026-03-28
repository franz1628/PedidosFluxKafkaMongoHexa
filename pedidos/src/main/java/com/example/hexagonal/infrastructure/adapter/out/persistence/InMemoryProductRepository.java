package com.example.hexagonal.infrastructure.adapter.out.persistence;

import com.example.hexagonal.application.port.out.ProductRepositoryPort;
import com.example.hexagonal.domain.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryProductRepository implements ProductRepositoryPort {

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private Long currentId = 1L;

    @Override
    public Mono<Product> save(Product product) {
        if (product.getId() == null) {
            product.setId(currentId.toString());
            currentId++;
        }
        products.put(product.getId(), product);
        return Mono.just(product);
    }

    @Override
    public Mono<Product> findById(String id) {
        return Mono.just(products.get(id));
    }

    @Override
    public Flux<Product> findAll() {
        return Flux.fromIterable(products.values());
    }

    @Override
    public Mono<Void> deleteById(String id) {
        products.remove(id);
        return Mono.empty();
    }
}
