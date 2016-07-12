package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.OrderRepository;
import com.thoughtworks.api.repository.PaymentRepository;
import com.thoughtworks.api.repository.ProductRepository;
import com.thoughtworks.api.repository.UserRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class OrderPaymentResourceTest extends ApiSupport {
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
        user = TestHelper.prepareUser(userRepository);
        product = TestHelper.prepareProduct(productRepository);
        order = TestHelper.prepareOrder(product, user, orderRepository);
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
        TestHelper.prepareOrderPayment(paymentRepository,order);

        Response response = target("/users/" + user.getId() + "/orders/" + order.getId() + "/payment")
                .request()
                .get();

        assertThat(response.getStatus(), is(200));
    }



}
