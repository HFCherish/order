package com.thoughtworks.api.repository.impl;

import com.thoughtworks.api.mappers.ProductMapper;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.repository.ProductRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepository {
    @Inject
    ProductMapper mapper;

    @Override
    public String nextId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public Optional<Product> ofId(String id) {
        return Optional.ofNullable(mapper.findById(id));
    }

    @Override
    public Product save(Product product) {
        mapper.save(product);
        return mapper.findById(product.getId());
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = mapper.findAll();
        return products;
    }
}
