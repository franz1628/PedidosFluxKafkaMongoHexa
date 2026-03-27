package com.example.inventarios.application.port.in;

import com.example.inventarios.domain.model.Order;

public interface ProcessOrderUseCase {
    void processOrder(Order order);
}
