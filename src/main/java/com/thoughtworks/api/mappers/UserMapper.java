package com.thoughtworks.api.mappers;

import com.thoughtworks.api.records.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int save(@Param("user") User user);

    User findById(@Param("id") String id);
}
