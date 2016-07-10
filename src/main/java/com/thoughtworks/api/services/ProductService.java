package com.thoughtworks.api.services;

import com.thoughtworks.api.mappers.ProductMapper;
import com.thoughtworks.api.records.Product;

import javax.inject.Inject;
import java.util.Optional;

public class ProductService {
    @Inject
    ProductMapper mapper;

    public Optional<Product> ofId(String id) {
        return Optional.ofNullable(mapper.findById(id));
    }
}
