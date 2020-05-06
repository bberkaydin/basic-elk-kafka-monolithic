package com.elasticsearch.test.repository;

import com.elasticsearch.test.model.User;

public interface UserRepository{
    User save(User user);
}
