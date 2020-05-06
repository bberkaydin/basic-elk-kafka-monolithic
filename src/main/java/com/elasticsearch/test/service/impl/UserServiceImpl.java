package com.elasticsearch.test.service.impl;

import com.elasticsearch.test.model.User;
import com.elasticsearch.test.repository.UserElasticSearchRepository;
import com.elasticsearch.test.repository.UserRepository;
import com.elasticsearch.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserElasticSearchRepository elasticSearchRepository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> findById(String Id) {
        return elasticSearchRepository.findById(Id);
    }

    @Override
    public User findByName(String name) {
        return elasticSearchRepository.findByName(name);
    }

    @Override
    public Iterable<User> findAll(){
       return elasticSearchRepository.findAll();
    }

    @Override
    public void deleteById(String Id) {
        elasticSearchRepository.deleteById(Id);
    }

    @Override
    public void deleteByName(String name) {
        elasticSearchRepository.deleteByName(name);
    }

    @Override
    public void deleteAll() {
        elasticSearchRepository.deleteAll();
    }


}
