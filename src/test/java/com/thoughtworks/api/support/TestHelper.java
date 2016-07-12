package com.thoughtworks.api.support;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.OrderRepository;
import com.thoughtworks.api.repository.ProductRepository;
import com.thoughtworks.api.repository.UserRepository;

import java.util.Arrays;

public class TestHelper {

    public static final double PRODUCT_PRICE = 1.1;
    public static final int ORDER_PRODUCT_QUANTITY = 2;
    public static final double ORDER_PRODUCT_AMOUNT = PRODUCT_PRICE * ORDER_PRODUCT_QUANTITY;

    public static Order prepareOrder(Product product, User user, OrderRepository orderRepository) {
        Order order = orderForTest(product);
        orderRepository.save(order, user.getId());
        return order;
    }

    public static Order orderForTest(Product product) {
        Order order = new Order();
        order.setName("Imran");
        order.setPhone("1243556578");
        order.setAddress("beijing");
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(product.getId());
        orderItem.setQuantity(ORDER_PRODUCT_QUANTITY);
        order.setOrderItems(Arrays.asList(orderItem));
        return order;
    }

    public static Product prepareProduct(ProductRepository productRepository) {
        Product product = productForTest(productRepository);
        productRepository.save(product);
        return product;
    }

    public static Product productForTest(ProductRepository productRepository) {
        return new Product().setId(productRepository.nextId())
                    .setName("apple")
                    .setDescription("red apple")
                    .setPrice(PRODUCT_PRICE)
                    .setRating(5);
    }

    public static User prepareUser(UserRepository userRepository) {
        User user = userForTest();
        userRepository.save(user);
        return user;
    }

    public static User userForTest() {
        User user = new User();
        user.setName("Petrina");
        return user;
    }
}