package com.basic.user.service;

import com.basic.user.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService{

    ResponseEntity<String> saveUser(User user);

    List<User> getUser();

    ResponseEntity<String> updateUser(User user);

    ResponseEntity<String> deleteUser(User user);
}
