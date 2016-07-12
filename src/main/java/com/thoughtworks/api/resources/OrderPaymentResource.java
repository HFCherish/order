package com.thoughtworks.api.resources;

import com.thoughtworks.api.jersey.Routes;
import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.Payment;
import com.thoughtworks.api.repository.PaymentRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class OrderPaymentResource {

    private Order order;

    public OrderPaymentResource(Order order) {
        this.order = order;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrder() {
        return order;
    }

    @POST
    @Path("payment")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response pay(Map<String, Object> payInfo,
                        @Context PaymentRepository paymentRepository,
                        @Context Routes routes) {
        Payment payment = new Payment(order.getId());
        payment.setAmount((double) payInfo.get("amount"));
        payment.setType((int) payInfo.get("pay_type"));

        paymentRepository.save(payment);
        return Response.created(routes.payment(order)).build();
    }

}
