package com.thoughtworks.api.repository.impl;

import com.thoughtworks.api.mappers.ProductMapper;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.repository.ProductRepository;

import javax.inject.Inject;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Inject
    ProductMapper mapper;

    @Override
    public Optional<Product> ofId(String id) {
        return Optional.ofNullable(mapper.findById(id));
    }

    @Override
    public Product save(Product product) {
        mapper.save(product);
        return mapper.findById(product.getId());
    }
}
