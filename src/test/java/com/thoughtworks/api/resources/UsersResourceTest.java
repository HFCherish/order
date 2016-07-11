package com.thoughtworks.api.resources;

import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ApiTestRunner.class)
public class UsersResourceTest extends ApiSupport{

//    private User user;
//
//    @Inject
//    UserRepositoryImpl userRepository;
//
//    @Before
//    public void setUp() {
//        user = new User();
//        user.setId(userRepository.nextId());
//        user.setName("Imran");
//    }

    @Test
    public void should_create_user() {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", "Imran");
        final Response response = target("/users").request().post(Entity.json(userInfo));
        assertThat(response.getStatus(), is(201));

    }

//    @Test
//    public void should_get_one_user_by_id() {
//
//
//    }
}