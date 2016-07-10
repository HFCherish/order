package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.services.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
public class ProductResource {
    private Product product;

    public ProductResource(Product product) {
        this.product = product;
    }

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("productId") String productId,
                              @Context ProductService prodService) {
        return prodService.ofId(productId)
                .map(product -> product)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
