package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.OrderRepository;
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
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class UserOrdersResourceTest extends ApiSupport {

    @Inject
    UserRepository userRepository;

    @Inject
    ProductRepository productRepository;
    @Inject
    OrderRepository orderRepository;

    private User user;
    private Product product;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        product = TestHelper.prepareProduct(productRepository);
        user = TestHelper.prepareUser(userRepository);
    }

    @Test
    public void should_create_order_for_some_user() {
        Map<String, Object> orderInfo = new HashMap<>();
        orderInfo.put("name", "Imran");
        orderInfo.put("address", "Australia");
        orderInfo.put("phone", "1320000000");
        Map<String, Object> orderItemInfo = new HashMap<>();
        orderItemInfo.put("product_id", product.getId());
        orderItemInfo.put("quantity", 2);
        List<Map<String, Object>> orderItems = new ArrayList<>();
        orderItems.add(orderItemInfo);
        orderInfo.put("order_item", orderItems);

        Response response = target("/users/" + user.getId() + "/orders").request().post(Entity.json(orderInfo));

        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_one_order() {
        Order order = TestHelper.prepareOrder(product,user,orderRepository);

        Response response = target("/users/" + user.getId() + "/orders/" + order.getId()).request().get();

        assertThat(response.getStatus(), is(200));
        Map orderInfo = response.readEntity(Map.class);
        assertThat(orderInfo.get("id").toString(), is(order.getId()));
        assertThat((double)orderInfo.get("totalPrice"), is(closeTo(2.2, 0.1)));
        List<Map<String, Object>> orderItems = (List<Map<String, Object>>) (orderInfo.get("orderItems"));
        assertThat(orderItems.get(0).get("productId").toString(), is(product.getId()));
        assertThat((int)orderItems.get(0).get("quantity"), is(2));
        assertThat((double)orderItems.get(0).get("amount"), is(closeTo(2.2, 0.1)));
    }

    @Test
    public void should_get_all_orders_of_some_user() {
        Order order = TestHelper.prepareOrder(product,user,orderRepository);

        Response response = target("/users/" + user.getId() + "/orders").request().get();

        assertThat(response.getStatus(), is(200));

    }
}