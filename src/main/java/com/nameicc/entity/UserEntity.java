package com.nameicc.entity;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

@Data
@Document(indexName = "test_user")
public class UserEntity implements Serializable {

    private String id;

    private String name;

    private int age;

}
