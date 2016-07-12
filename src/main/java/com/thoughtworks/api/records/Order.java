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
    protected double totalPrice;
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
