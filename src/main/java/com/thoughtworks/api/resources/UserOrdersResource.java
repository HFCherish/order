package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.User;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class UserOrdersResource {
    public UserOrdersResource(User user) {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response buildOrder() {
        return Response.noContent().build();
    }
}
