package com.thoughtworks.api.services;

import com.thoughtworks.api.records.Product;

import java.util.Optional;

public interface ProductService {
    Optional<Product> ofId(String id);

    Product create(Product product);
}
