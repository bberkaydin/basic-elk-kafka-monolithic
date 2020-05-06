package com.elasticsearch.test.service;

import com.elasticsearch.test.model.User;

import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> findById(String Id);
    public User findByName(String name);
    void deleteById(String Id);
    void deleteByName(String name);
    void deleteAll();
    Iterable<User> findAll();
}
