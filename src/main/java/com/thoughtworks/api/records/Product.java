package com.thoughtworks.api.records;

public class Product {
    protected String id;
    protected String name;
    protected String description;
    protected double price;
    protected int rating;

    public Product(String id) {
        this.id = id;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getRating() {
        return rating;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product setPrice(double price) {
        this.price = price;
        return this;
    }

    public Product setRating(int rating) {
        this.rating = rating;
        return this;
    }
}
