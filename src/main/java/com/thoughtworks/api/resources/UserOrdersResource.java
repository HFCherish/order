package com.thoughtworks.api.resources;

import com.thoughtworks.api.jersey.Routes;
import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.OrderItem;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.OrderRepository;
import com.thoughtworks.api.repository.ProductRepository;

import javax.ws.rs.*;
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
                               @Context ProductRepository productRepository,
                               @Context Routes routes) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (Map<String, Object> orderItemInfo : (List<Map<String, Object>>) orderInfo.get("order_item")) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(orderItemInfo.get("product_id").toString());
            orderItem.setQuantity((int) orderItemInfo.get("quantity"));
            orderItems.add(orderItem);
        }

        Order order = new Order();
        order.setName(orderInfo.get("name").toString());
        order.setAddress(orderInfo.get("address").toString());
        order.setPhone(orderInfo.get("phone").toString());
        order.setOrderItems(orderItems);

        orderRepository.save(order, user.getId());
        return Response.created(routes.order(user)).build();
    }

    @Path("{orderId}")
    public  OrderPaymentResource getOrder(@PathParam("orderId") String orderId,
                           @Context OrderRepository orderRepository) {
        return orderRepository.ofId(orderId)
                .map(OrderPaymentResource::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  List<Order> getAllOrders(@Context OrderRepository orderRepository) {
        return orderRepository.findAllOfUser(user.getId())
                .map(orders -> orders)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

}
