package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Order;

import java.util.Optional;

public interface OrderRepository {
    void save(Order order, String userId);

    Optional<Order> ofId(String orderId);
}
