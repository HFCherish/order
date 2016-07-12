package com.thoughtworks.api.records;

import java.util.UUID;

public class User {
    protected String id;
    protected String name;

    public User() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }
}
