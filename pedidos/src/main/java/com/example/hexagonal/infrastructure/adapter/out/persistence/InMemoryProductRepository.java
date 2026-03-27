package com.example.hexagonal.infrastructure.adapter.out.persistence;

import com.example.hexagonal.application.port.out.ProductRepositoryPort;
import com.example.hexagonal.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryProductRepository implements ProductRepositoryPort {

    private final Map<String, Product> products = new ConcurrentHashMap<>();
    private Long currentId = 1L;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(currentId.toString());
            currentId++;
        }
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public void deleteById(String id) {
        products.remove(id);
    }
}
