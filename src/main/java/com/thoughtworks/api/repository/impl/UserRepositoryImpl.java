package com.thoughtworks.api.repository.impl;

import com.thoughtworks.api.mappers.UserMapper;
import com.thoughtworks.api.records.User;
import com.thoughtworks.api.repository.UserRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {
    @Inject
    UserMapper mapper;

    @Override
    public User save(User user) {
        mapper.save(user);
        return mapper.findById(user.getId());
    }

    @Override
    public Optional<User> ofId(String userId) {
        return Optional.ofNullable(mapper.findById(userId));
    }
}
