package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void save(@Param("order") Order order, @Param("userId") String userId);

    Order findById(@Param("orderId") String orderId);

    List<Order> findAllOfUser(@Param("userId") String userId);
}
