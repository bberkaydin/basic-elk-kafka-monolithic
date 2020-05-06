package com.elasticsearch.test.repository.impl;

import com.elasticsearch.test.model.User;
import com.elasticsearch.test.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final String INDEX = "user-index";
    private final String TYPE = "users";

    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;

    @Autowired
    public UserRepositoryImpl(ObjectMapper objectMapper, @Qualifier("client") RestHighLevelClient restHighLevelClient) {
        this.objectMapper = objectMapper;
        this.restHighLevelClient = restHighLevelClient;
    }

    @Override
    public User save(User user) {
        //user.setId(UUID.randomUUID().toString());
        Map<String, Object> dataMap = objectMapper.convertValue(user, Map.class);
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, user.getId())
                .source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest);
        } catch(ElasticsearchException e) {
            e.getDetailedMessage();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return user;
    }
}
