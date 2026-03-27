package com.example.inventarios.application.usecase;

import com.example.inventarios.application.port.in.ProcessOrderUseCase;
import com.example.inventarios.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessOrderUseCaseImpl implements ProcessOrderUseCase {
    private static final Logger log = LoggerFactory.getLogger(ProcessOrderUseCaseImpl.class);

    @Override
    public void processOrder(Order order) {
        log.info("Processing order for Inventory: OrderId={}, CustomerId={}", order.getId(), order.getCustomerId());
        
        order.getItems().forEach(item -> {
            log.info("Updating stock for ProductId={}, Quantity={}", item.getProductId(), item.getQuantity());
            // Here we would normally update the stock in the DB
        });
    }
}
