package com.elasticsearch.test.controller;

import com.elasticsearch.test.model.User;
import com.elasticsearch.test.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserRestController {

    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    UserService service;

    @GetMapping("/api")
    public String getapi(){
        logger.info("TESTING CONNECTION");
        return "api";
    }

    @PostMapping("/save-user")
    public User saveUser(@RequestBody User user) {
        logger.info("SAVEING USER");
        return service.save(user);
    }

    @GetMapping("/find-id/{id}")
    public Optional<User> getUserByID(@PathVariable String id){
        logger.info("FINDING ID: " + id);
        return service.findById(id);
    }

    @GetMapping("/find-name/{name}")
    public User getUserByName(@PathVariable String name){
        logger.info("FINDING NAME: " + name);
        return service.findByName(name);
    }

    @GetMapping("/find-all")
    public Iterable<User> findAll() {
        logger.info("FINDING ALL");
        return service.findAll();
    }

    @DeleteMapping("/delete-id/{id}")
    public void deleteUserByID(@PathVariable String Id){
        service.deleteById(Id);
    }

    @DeleteMapping("/delete-name/{name}")
    public void deleteUserByName(@PathVariable String name){
        service.deleteByName(name);
    }

    @DeleteMapping("/delete-all")
    public void deleteAll(){
        service.deleteAll();
    }

}
