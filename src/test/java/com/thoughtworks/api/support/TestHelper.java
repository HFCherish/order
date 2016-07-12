package com.thoughtworks.api.support;

import com.thoughtworks.api.records.*;
import com.thoughtworks.api.repository.OrderRepository;
import com.thoughtworks.api.repository.PaymentRepository;
import com.thoughtworks.api.repository.ProductRepository;
import com.thoughtworks.api.repository.UserRepository;

import java.util.Arrays;

public class TestHelper {

    public static final double PRODUCT_PRICE = 1.1;
    public static final int ORDER_PRODUCT_QUANTITY = 2;
    public static final double ORDER_PRODUCT_AMOUNT = PRODUCT_PRICE * ORDER_PRODUCT_QUANTITY;
    public static final double ORDER_PAYMENT_AMOUNT = 3.0;
    public static final int ORDER_PAY_BY_CASH = 0;

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

    public static Payment prepareOrderPayment(PaymentRepository paymentRepository, Order order) {
        Payment payment = paymentForTest(order);
        paymentRepository.save(payment);
        return payment;
    }

    public static Payment paymentForTest(Order order) {
        Payment payment = new Payment(order.getId());
        payment.setAmount(ORDER_PAYMENT_AMOUNT);
        payment.setType(ORDER_PAY_BY_CASH);
        return payment;
    }
}