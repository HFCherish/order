package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.services.ProductService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

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

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response create(Map<String, Object> prodInfo,
            @Context ProductService prodService) {
        prodService.create(new Product().setId("00" + 2)
                .setName(prodInfo.get("name").toString())
                .setDescription(prodInfo.get("description").toString())
                .setPrice(Double.valueOf(prodInfo.get("price").toString()))
                .setRating(5));
        return Response.status(201).build();
    }
}
