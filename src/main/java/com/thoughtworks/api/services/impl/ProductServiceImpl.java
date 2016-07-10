package com.thoughtworks.api.services.impl;

import com.thoughtworks.api.mappers.ProductMapper;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.services.ProductService;

import javax.inject.Inject;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Inject
    ProductMapper mapper;

    @Override
    public Optional<Product> ofId(String id) {
        return Optional.ofNullable(mapper.findById(id));
    }

    @Override
    public Product create(Product product) {
        mapper.save(product);
        return mapper.findById(product.getId());
    }
}
