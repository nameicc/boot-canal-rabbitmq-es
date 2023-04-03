package com.nameicc.service;


import com.nameicc.entity.UserEntity;

public interface UserService {

    void insert(UserEntity user);

    void update(UserEntity user);

    void delete(UserEntity user);

    UserEntity findUserByName(String name);

}
