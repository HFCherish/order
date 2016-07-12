package com.thoughtworks.api.jersey;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }

    public URI product(Product product) {
        String url = baseUri + "products/" + product.getId();
        return URI.create(url);
    }

    public URI user(User user) {
        return URI.create(baseUri + "users/" + user.getId());
    }

    public URI order(User user) {
        return URI.create(baseUri + "users/" + user.getId() + "/orders");
    }

    public URI payment(Order order) {
        return URI.create(baseUri + "users/" + order.getUserId() + "/orders/" + order.getId() + "/payment");
    }
}
