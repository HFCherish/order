package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.OrderItem;
import org.apache.ibatis.annotations.Param;

public interface OrderItemMapper {
    void save(@Param("orderItem") OrderItem orderItem, @Param("orderId") String orderId);
}
