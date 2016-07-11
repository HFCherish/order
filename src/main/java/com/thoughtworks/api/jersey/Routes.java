package com.thoughtworks.api.jersey;

import com.thoughtworks.api.records.Product;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = "/";
    }

    public URI product(Product product) {
        System.out.println("***********************somethong: ");
        String url = baseUri + "products/" + product.getId();
        System.out.println("***********************url: " + url);
        return URI.create(url);
    }
}
