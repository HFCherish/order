package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.OrderRepository;
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
import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

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
        prepare_user_and_product_for_order();
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

    private void prepare_user_and_product_for_order() {
        user = new User();
        userRepository.save(user.setName("Petrina"));

        product = new Product().setId(productRepository.nextId())
                .setName("apple")
                .setDescription("red apple")
                .setPrice(1.1)
                .setRating(5);
        productRepository.save(product);
    }

    @Test
    public void should_get_one_order() {
        Order order = getDefaultOrder();
        orderRepository.save(order,user.getId());

        Response response = target("/users/" + user.getId() + "/orders/" +order.getId()).request().get();
        assertThat(response.getStatus(), is(200));

    }

    private Order getDefaultOrder() {
        Order order = new Order();
        order.setName("Imran");
        order.setPhone("1243556578");
        order.setAddress("beijing");
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(product.getId());
        orderItem.setQuantity(2);
        order.setOrderItems(Arrays.asList(orderItem));
        return order;
    }
}