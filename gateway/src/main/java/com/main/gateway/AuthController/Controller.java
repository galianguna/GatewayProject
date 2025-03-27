package com.main.gateway.AuthController;


import com.main.gateway.AuthService.AuthService;
import com.main.gateway.model.UserDls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class Controller {

    @Autowired
    AuthService authService;
    @PostMapping("/register")
    public String register(@RequestBody UserDls userDls){
        return authService.register(userDls);
    }

}
