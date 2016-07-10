package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.Product;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ProductMapper {
    Product findById(@Param("id") String id);
    int save(@Param("product") Product product);
}
