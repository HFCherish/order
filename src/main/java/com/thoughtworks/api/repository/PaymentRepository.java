package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Payment;

public interface PaymentRepository {
    void save(Payment payment);
}
