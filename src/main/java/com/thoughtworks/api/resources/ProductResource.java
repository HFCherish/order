package com.thoughtworks.api.resources;

import com.thoughtworks.api.jersey.Routes;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.repository.ProductRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/products")
public class ProductResource {

    @GET
    @Path("{productId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam("productId") String productId,
                              @Context ProductRepository prodRepository) {
        return prodRepository.ofId(productId)
                .map(product -> product)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> prodInfo,
                           @Context ProductRepository productRepository,
                           @Context Routes routes) {
        Product prod = productRepository.save(new Product().setId(productRepository.nextId())
                .setName(prodInfo.get("name").toString())
                .setDescription(prodInfo.get("description").toString())
                .setPrice(Double.valueOf(prodInfo.get("price").toString()))
                .setRating(5));

        return Response.created(routes.product(prod)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProducts(@Context ProductRepository productRepository) {
        return productRepository.findAll();
    }
}
