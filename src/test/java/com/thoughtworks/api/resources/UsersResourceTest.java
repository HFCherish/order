package com.thoughtworks.api.resources;

import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.UserRepository;
import com.thoughtworks.api.support.ApiSupport;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class UsersResourceTest extends ApiSupport{

    @Inject
    UserRepository userRepository;

    @Test
    public void should_create_user() {
        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("name", "Imran");
        final Response response = target("/users").request().post(Entity.json(userInfo));
        assertThat(response.getStatus(), is(201));
    }
    @Test
    public void should_get_user_by_id() throws Exception {
        User user = TestHelper.userForTest();
        userRepository.save(user);
        final Response response = get("/users/" + user.getId());
        assertThat(response.getStatus(), is(200));
        final Map userMap = response.readEntity(Map.class);
        assertThat(userMap.get("id"), is(user.getId()));
        assertThat(userMap.get("name"), is(user.getName()));
    }

}