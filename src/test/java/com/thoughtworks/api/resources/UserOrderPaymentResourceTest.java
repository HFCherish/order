package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.*;
import com.thoughtworks.api.repository.OrderRepository;
import com.thoughtworks.api.repository.PaymentRepository;
import com.thoughtworks.api.repository.ProductRepository;
import com.thoughtworks.api.repository.UserRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UserOrderPaymentResourceTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;
    @Inject
    OrderRepository orderRepository;

    @Inject
    PaymentRepository paymentRepository;

    private User user;
    private Product product;
    private Order order;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        prepare_user_and_product_and_order_for_payment();
    }

    @Test
    public void should_create_payment() {
        Map<String, Object> payInfo = new HashMap<>();
        payInfo.put("pay_type", 0);
        payInfo.put("amount", 2.2);

        Response response = target("/users/" + user.getId() + "/orders/" + order.getId() + "/payment")
                .request()
                .post(Entity.json(payInfo));

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_payment_of_some_order_of_some_user() {
        Payment payment = new Payment(order.getId());
        payment.setAmount(3.0);
        payment.setType(0);
        paymentRepository.save(payment);

        Response response = target("/users/" + user.getId() + "/orders/" + order.getId() + "/payment")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }

    private void prepare_user_and_product_and_order_for_payment() {
        user = new User();
        userRepository.save(user.setName("Petrina"));

        product = new Product().setId(productRepository.nextId())
                .setName("apple")
                .setDescription("red apple")
                .setPrice(1.1)
                .setRating(5);
        productRepository.save(product);

        order = new Order();
        order.setName("Imran");
        order.setPhone("1243556578");
        order.setAddress("beijing");
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(product.getId());
        orderItem.setQuantity(2);
        order.setOrderItems(Arrays.asList(orderItem));
        orderRepository.save(order, user.getId());
    }
}
