package com.thoughtworks.api.repository.impl;

import com.thoughtworks.api.mappers.OrderItemMapper;
import com.thoughtworks.api.mappers.OrderMapper;
import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.repository.OrderRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Inject
    OrderMapper orderMapper;

    @Inject
    OrderItemMapper orderItemMapper;

    @Override
    public void save(Order order, String userId) {
        orderMapper.save(order, userId);
        for(OrderItem orderItem: order.getOrderItems()) {
            orderItemMapper.save(orderItem, order.getId());
        }
    }

    @Override
    public Optional<Order> ofId(String orderId) {
        return Optional.ofNullable(orderMapper.findById(orderId));
    }

    @Override
    public Optional<List<Order>> findAllOfUser(String userId) {
        return Optional.ofNullable(orderMapper.findAllOfUser(userId));
    }
}
