package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ApiTestRunner.class)
public class ProductRepositoryTest {
    @Inject
    ProductRepository productRepository;

    @Test
    public void should_create_and_get_product() {
        Product product = TestHelper.productForTest(productRepository);

        productRepository.save(product);
        Optional<Product> fetched = productRepository.ofId(product.getId());

        assertThat(fetched.isPresent(), is(true));
        Product fetchedProd = fetched.get();
        assertThat(fetchedProd.getName(), is(product.getName()));
        assertThat(fetchedProd.getPrice(), is(product.getPrice()));
        assertThat(fetchedProd.getDescription(), is(product.getDescription()));
        assertThat(fetchedProd.getRating(), is(product.getRating()));
    }

    @Test
    public void should_get_all_products() {
        Product product = TestHelper.prepareProduct(productRepository);

        List<Product> fetchedProds = productRepository.findAll();

        assertThat(fetchedProds.size(), is(1));

    }
}