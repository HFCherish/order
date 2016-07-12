package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.Order;
import com.thoughtworks.api.records.Payment;
import com.thoughtworks.api.records.Product;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static com.thoughtworks.api.support.TestHelper.*;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;



@RunWith(ApiTestRunner.class)
public class PaymentRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    OrderRepository orderRepository;

    @Inject
    PaymentRepository paymentRepository;

    @Test
    public void should_save_and_get_payment() {
        Order order = prepareOrder(prepareProduct(productRepository), prepareUser(userRepository),orderRepository);
        Payment payment = paymentForTest(order);

        paymentRepository.save(payment);
        Optional<Payment> fetched = paymentRepository.findByOrderId(order.getId());

        assertThat(fetched.isPresent(), is(true));
        Payment fetchedPayment = fetched.get();
        assertThat(fetchedPayment.getOrderId(), is(payment.getOrderId()));
        assertThat(fetchedPayment.getAmount(), is(payment.getAmount()));
        assertThat(fetchedPayment.getType(), is(payment.getType()));
        assertThat(fetchedPayment.getPayAt(), is(notNullValue(DateTime.class)));
    }
}