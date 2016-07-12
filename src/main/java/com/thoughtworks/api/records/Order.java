package com.thoughtworks.api.records;

import org.joda.time.DateTime;

import java.util.List;
import java.util.UUID;

public class Order {
    protected String id;
    protected String userId;
    protected String name;
    protected String address;
    protected String phone;
    protected DateTime createdAt;
    protected double totalPrice;
    protected boolean payState;
    protected List<OrderItem> orderItems;

    public Order() {
        this.id = UUID.randomUUID().toString();
    }

    public double getTotalPrice() {
        totalPrice = 0;
        for( OrderItem orderItem: orderItems) {
            totalPrice += orderItem.getAmount();
        }
        return totalPrice;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isPayState() {
        return payState;
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

    public DateTime getCreatedAt() {
        return createdAt;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
