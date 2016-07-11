package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.User;

import javax.ws.rs.Path;

public class UserResource {

    private User user;

    public UserResource(User user) {
        this.user = user;
    }

    @Path("orders")
    public UserOrdersResource userOrdersResource() {
        return new UserOrdersResource(user);
    }
}
