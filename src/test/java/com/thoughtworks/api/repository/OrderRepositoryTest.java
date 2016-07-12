package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class OrderRepositoryTest {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;

    @Inject
    OrderRepository orderRepository;

    private User user;
    private Product product;

    @Before
    public void setUp() {
        user = TestHelper.prepareUser(userRepository);
        product = TestHelper.prepareProduct(productRepository);
    }

    @Test
    public void should_create_and_get_order() {
        //given
        Order order = TestHelper.orderForTest(product);

        //when
        orderRepository.save(order, user.getId());
        final Optional<Order> fetched = orderRepository.ofId(order.getId());

        //then
        assertThat(fetched.isPresent(), is(true));
        final Order fetchedOrder = fetched.get();
        assertThat(fetchedOrder.getUserId(), is(user.getId()));
        assertThat(fetchedOrder.getId(), is(order.getId()));
        assertThat(fetchedOrder.getTotalPrice(), is(order.getTotalPrice()));
        assertThat(fetchedOrder.getOrderItems().size(), is(1));
        final OrderItem orderItem = fetchedOrder.getOrderItems().get(0);
        assertThat(orderItem.getProductId(), is(product.getId()));
        assertThat(orderItem.getAmount(), is(closeTo(TestHelper.ORDER_PRODUCT_AMOUNT, 0.1)));
    }

    @Test
    public void should_get_all_orders_of_user() {
        Order order = TestHelper.prepareOrder(product, user, orderRepository);

        Optional<List<Order>> fetched = orderRepository.findAllOfUser(user.getId());

        assertThat(fetched.isPresent(), is(true));
        List<Order> fetchedOrders = fetched.get();
        assertThat(fetchedOrders.size(), is(1));
        assertThat(fetchedOrders.get(0).getId(), is(order.getId()));

    }
}