package com.basic.user.controller;

import com.basic.user.model.User;
import com.basic.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /* can access this API without credentials
    * */
    @GetMapping("/main")
    public String main(){
        return "i'm the only one you can access without credentials!!";
    }
    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping
    public List<User> getUser(){
        return userService.getUser();
    }

    @PutMapping
    public ResponseEntity<String> updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody User user){

        return userService.deleteUser(user);
    }
}
