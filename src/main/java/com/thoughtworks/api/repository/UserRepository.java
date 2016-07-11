package com.thoughtworks.api.repository;

import com.thoughtworks.api.records.User;

import java.util.Optional;

public interface UserRepository {
    String nextId();

    User save(User user);

    Optional<User> ofId(String userId);
}
