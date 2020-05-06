package com.elasticsearch.test.repository;

import com.elasticsearch.test.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserElasticSearchRepository extends ElasticsearchRepository<User , String> {
    User findByName(String name);
    void deleteByName(String name);
}
