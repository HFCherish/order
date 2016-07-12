package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.User;
import com.thoughtworks.api.support.ApiTestRunner;
import com.thoughtworks.api.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ApiTestRunner.class)
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void should_create_and_get_user() {
        User user = TestHelper.userForTest();

        userRepository.save(user);
        Optional<User> fetched = userRepository.ofId(user.getId());

        assertThat(fetched.isPresent(), is(true));
        User fetchedUser = fetched.get();
        assertThat(fetchedUser.getName(), is(user.getName()));

    }
}