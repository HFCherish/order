package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    Product findById(@Param("id") String id);
}
