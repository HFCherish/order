package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.Order;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    void save(@Param("order") Order order, @Param("userId") String userId);
}
