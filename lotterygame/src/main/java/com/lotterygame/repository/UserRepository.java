package com.lotterygame.repository;

import com.lotterygame.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User,Long> {

    User findByUserName(String UserName);

    ArrayList<User> findAll();
}
