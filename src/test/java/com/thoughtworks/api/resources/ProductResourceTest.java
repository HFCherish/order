package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.repository.ProductRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(ApiTestRunner.class)
public class ProductResourceTest extends ApiSupport {

    private Product product;
    @Inject
    ProductRepository productRepository;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        product = new Product().setId(productRepository.nextId())
                .setName("apple")
                .setDescription("red apple")
                .setPrice(1.1)
                .setRating(5);
    }

    @Test
    public void should_get_one_product_by_id() throws Exception {
        productRepository.save(product);

        final Response response = get("/products/" + product.getId());

        assertThat(response.getStatus(), is(200));

        Map prod = response.readEntity(Map.class);
        verifySameProduct(prod, product);
    }

    @Test
    public void should_create_product() throws Exception {
        HashMap<String, Object> prod = new HashMap<>();
        prod.put("name", "orange");
        prod.put("description", "great orange");
        prod.put("price", 1.2);
        final Response response = target("/products").request().post(Entity.json(prod));
        assertThat(response.getStatus(), is(201));
    }

    @Test
    public void should_get_all_products() {
        //before
        productRepository.save(product);

        //when
        final Response response = get("/products/");

        //then
        assertThat(response.getStatus(), is(200));
        List prods = response.readEntity(List.class);
        assertThat(prods.size(), is(1));
        Map prod = (Map) prods.get(0);
        verifySameProduct(prod, product);

    }

    private void verifySameProduct(Map prod, Product anotherProd) {
        assertThat(prod.get("id"), is(anotherProd.getId()));
        assertThat(prod.get("name"), is(anotherProd.getName()));
        assertThat(prod.get("description"), is(anotherProd.getDescription()));
        assertThat(prod.get("price"), is(anotherProd.getPrice()));
        assertThat(prod.get("rating"), is(anotherProd.getRating()));
    }
}