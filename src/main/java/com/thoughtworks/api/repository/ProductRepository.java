package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    String nextId();

    Optional<Product> ofId(String id);

    Product save(Product product);

    List<Product> findAll();
}
