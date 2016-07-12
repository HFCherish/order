package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    void save(Order order, String userId);

    Optional<Order> ofId(String orderId);

    Optional<List<Order>> findAllOfUser(String userId);
}
