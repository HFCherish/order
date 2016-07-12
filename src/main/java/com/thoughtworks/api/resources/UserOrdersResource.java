package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.OrderRepository;
import com.thoughtworks.api.repository.ProductRepository;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserOrdersResource {
    private User user;

    public UserOrdersResource(User user) {
        this.user = user;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder(Map<String, Object> orderInfo,
                               @Context OrderRepository orderRepository,
                               @Context ProductRepository productRepository) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map<String, Object> orderItemInfo : (List<Map<String, Object>>) orderInfo.get("order_item")) {
            OrderItem orderItem = new OrderItem();
            String product_id = orderItemInfo.get("product_id").toString();
            orderItem.setProductId(product_id);
            int quantity = (int) orderItemInfo.get("quantity");
            orderItem.setQuantity(quantity);
            Product product = productRepository.ofId(product_id)
                    .map(prod -> prod)
                    .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
            orderItem.setAmount(product.getPrice());
            orderItems.add(orderItem);
        }

        Order order = new Order();
        order.setName(orderInfo.get("name").toString());
        order.setAddress(orderInfo.get("address").toString());
        order.setPhone(orderInfo.get("phone").toString());
        order.setOrderItems(orderItems);
        return Response.noContent().build();
    }
}
