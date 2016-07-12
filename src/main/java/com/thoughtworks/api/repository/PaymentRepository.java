package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Payment;

import java.util.Optional;

public interface PaymentRepository {
    void save(Payment payment);

    Optional<Payment> findByOrderId(String orderId);
}
