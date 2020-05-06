package com.elasticsearch.test.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(indexName = "user-index", type = "users")
public class User {

    @Id
    private String id;
    private String name;
    private String surname;
    private int age;
    private String membership;
}
