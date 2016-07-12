package com.thoughtworks.api.records;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Order {
    protected String id;
    protected String name;
    protected String address;
    protected String phone;
    protected Date createdAt;
    protected List<OrderItem> orderItems;

    public Order() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
