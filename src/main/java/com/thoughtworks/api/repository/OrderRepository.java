package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Order;

public interface OrderRepository {
    void save(Order order, String userId);
}
