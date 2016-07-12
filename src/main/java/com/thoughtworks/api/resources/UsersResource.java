package com.thoughtworks.api.resources;

import com.thoughtworks.api.jersey.Routes;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("users")
public class UsersResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> userInfo,
                               @Context UserRepository userRepository,
                               @Context Routes routes) {
        User user = userRepository.save(new User().setName(userInfo.get("name").toString()));
        return Response.created(routes.user(user)).build();
    }

    @Path("{userId}")
    public UserResource getUser(@PathParam("userId") String userId,
                                @Context UserRepository userRepository) {
        return userRepository.ofId(userId)
                .map(UserResource::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }
}
