package com.thoughtworks.api.repository.impl;

import com.thoughtworks.api.mappers.PaymentMapper;
import com.thoughtworks.api.records.Payment;
import com.thoughtworks.api.repository.PaymentRepository;

import javax.inject.Inject;

public class PaymentRepositoryImpl implements PaymentRepository {
    @Inject
    PaymentMapper paymentMapper;

    @Override
    public void save(Payment payment) {
        paymentMapper.save(payment);
    }
}
