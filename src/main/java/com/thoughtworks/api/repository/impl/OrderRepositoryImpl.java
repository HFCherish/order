package com.thoughtworks.api.repository.impl;

import com.thoughtworks.api.mappers.OrderItemMapper;
import com.thoughtworks.api.mappers.OrderMapper;
import com.thoughtworks.api.mappers.ProductMapper;
import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.repository.OrderRepository;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {
    @Inject
    OrderMapper orderMapper;

    @Inject
    OrderItemMapper orderItemMapper;

    @Inject
    ProductMapper productMapper;

    @Override
    public void save(Order order, String userId) {
        calAmountForAllItems(order);
        orderMapper.save(order, userId);
        for(OrderItem orderItem: order.getOrderItems()) {
            orderItemMapper.save(orderItem, order.getId());
        }
    }

    private void calAmountForAllItems(Order order) {
        for( OrderItem orderItem: order.getOrderItems()) {
            Product product = productMapper.findById(orderItem.getProductId());
            orderItem.setAmount(product.getPrice() * orderItem.getQuantity());
        }
    }

    @Override
    public Optional<Order> ofId(String orderId) {
        Order order = orderMapper.findById(orderId);
        return Optional.ofNullable(order);
    }


    @Override
    public Optional<List<Order>> findAllOfUser(String userId) {
        return Optional.ofNullable(orderMapper.findAllOfUser(userId));
    }
}
