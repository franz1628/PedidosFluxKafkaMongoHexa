package com.example.inventarios.application.usecase;

import com.example.inventarios.application.port.in.ProcessOrderUseCase;
import com.example.inventarios.application.port.out.InventoryRepository;
import com.example.inventarios.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProcessOrderUseCaseImpl implements ProcessOrderUseCase {
    private static final Logger log = LoggerFactory.getLogger(ProcessOrderUseCaseImpl.class);

    private final InventoryRepository inventoryRepository;

    public ProcessOrderUseCaseImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Mono<Void> processOrder(Order order) {
        log.info("Processing order for Inventory: OrderId={}, CustomerId={}", order.getId(), order.getCustomerId());
        
        return Flux.fromIterable(order.getItems())
            .flatMap(item -> {
                log.info("Attempting to update stock for ProductId={}, Quantity requested={}", item.getProductId(), item.getQuantity());
                
                return inventoryRepository.findByProductId(item.getProductId())
                    .flatMap(inventoryItem -> {
                        if (inventoryItem.getQuantity() >= item.getQuantity()) {
                            inventoryItem.setQuantity(inventoryItem.getQuantity() - item.getQuantity());
                            return inventoryRepository.save(inventoryItem)
                                .doOnSuccess(saved -> log.info("Stock successfully updated for ProductId={}. New quantity: {}", item.getProductId(), saved.getQuantity()))
                                .then();
                        } else {
                            log.warn("Insufficient stock for ProductId={}. Requested: {}, Available: {}", 
                                item.getProductId(), item.getQuantity(), inventoryItem.getQuantity());
                            return Mono.empty();
                        }
                    })
                    .switchIfEmpty(Mono.defer(() -> {
                        log.error("Product not found in inventory: ProductId={}", item.getProductId());
                        return Mono.empty();
                    }));
            })
            .then();
    }
}
