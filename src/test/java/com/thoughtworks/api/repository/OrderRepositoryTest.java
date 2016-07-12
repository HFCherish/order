package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ApiTestRunner.class)
public class OrderRepositoryTest extends ApiSupport{
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    private User user;
    private Product product;

    @Test
    public void should_create_and_get_order() {
        user = TestHelper.prepareUser(userRepository);
        product = TestHelper.prepareProduct(productRepository);
        Order order = TestHelper.orderForTest(product);

        orderRepository.save(order, user.getId());
        final Optional<Order> fetched = orderRepository.ofId(order.getId());

        assertThat(fetched.isPresent(), is(true));
        final Order fetchedOrder = fetched.get();
        assertThat(fetchedOrder.getUserId(), is(user.getId()));
        assertThat(fetchedOrder.getId(), is(order.getId()));
        assertThat(fetchedOrder.getTotalPrice(), is(order.getTotalPrice()));
        assertThat(fetchedOrder.getOrderItems().size(), is(1));
        final OrderItem orderItem = fetchedOrder.getOrderItems().get(0);
        assertThat(orderItem.getProductId(), is(product.getId()));
        assertThat(orderItem.getAmount(), is(2.2));
    }
}