package com.thoughtworks.api.records;

import org.joda.time.DateTime;

public class Payment {
    protected String orderId;
    int type;
    double amount;
    DateTime payAt;

    public Payment(String orderId) {
        this.orderId = orderId;
    }

    public static final int CASH = 0;

    public String getOrderId() {
        return orderId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DateTime getPayAt() {
        return payAt;
    }

}
