package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.support.ApiSupport;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProductResourceTest extends ApiSupport {

    private Product product;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        product = new Product().setId("001")
                .setName("apple")
                .setDescription("red apple")
                .setPrice(1.1)
                .setRating(5);
    }

    @Test
    public void should_get_one_product_by_id() throws Exception {

        final Response response = get("/products/" + product.getId());

        assertThat(response.getStatus(), is(200));

        Map prod = response.readEntity(Map.class);
        assertThat(prod.get("id"), is(equals(product.getId())));
        assertThat(prod.get("name"), is(equals(product.getName())));
        assertThat(prod.get("description"), is(equals(product.getDescription())));
        assertThat(prod.get("price"), is(product.getPrice()));
        assertThat(prod.get("rating"), is(product.getRating()));
    }

    @Test
    public void should_create_product() throws Exception {
        HashMap<String, Object> prod = new HashMap<>();
        prod.put("name", "orange");
        prod.put("description", "great orange");
        prod.put("price", 1.2);
        final Response response = target("/products").request().post(Entity.json(prod));
        assertThat(response.getStatus(), CoreMatchers.is(201));
    }

}