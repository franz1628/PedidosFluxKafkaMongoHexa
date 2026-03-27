package com.example.inventarios.application.usecase;

import com.example.inventarios.application.port.in.ProcessOrderUseCase;
import com.example.inventarios.application.port.out.InventoryRepository;
import com.example.inventarios.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessOrderUseCaseImpl implements ProcessOrderUseCase {
    private static final Logger log = LoggerFactory.getLogger(ProcessOrderUseCaseImpl.class);

    private final InventoryRepository inventoryRepository;

    public ProcessOrderUseCaseImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public void processOrder(Order order) {
        log.info("Processing order for Inventory: OrderId={}, CustomerId={}", order.getId(), order.getCustomerId());
        
        order.getItems().forEach(item -> {
            log.info("Attempting to update stock for ProductId={}, Quantity requested={}", item.getProductId(), item.getQuantity());
            
            inventoryRepository.findByProductId(item.getProductId()).ifPresentOrElse(
                inventoryItem -> {
                    if (inventoryItem.getQuantity() >= item.getQuantity()) {
                        inventoryItem.setQuantity(inventoryItem.getQuantity() - item.getQuantity());
                        inventoryRepository.save(inventoryItem);
                        log.info("Stock successfully updated for ProductId={}. New quantity: {}", item.getProductId(), inventoryItem.getQuantity());
                    } else {
                        log.warn("Insufficient stock for ProductId={}. Requested: {}, Available: {}", 
                            item.getProductId(), item.getQuantity(), inventoryItem.getQuantity());
                    }
                },
                () -> log.error("Product not found in inventory: ProductId={}", item.getProductId())
            );
        });
    }
}
