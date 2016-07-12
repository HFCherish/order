package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentMapper {
    void save(@Param("payment") Payment payment);
}
