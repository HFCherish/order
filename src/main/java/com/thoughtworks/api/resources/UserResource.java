package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.User;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

public class UserResource {

    private User user;

    public UserResource(User user) {
        this.user = user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return user;
    }

    @Path("orders")
    public UserOrdersResource userOrdersResource() {
        return new UserOrdersResource(user);
    }
}
