package com.main.gateway.AuthService;

import com.main.gateway.model.UserDls;
import com.main.gateway.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public String register(UserDls userDls) {

        if(!userDls.isNull())
            return "user,password,role should not null";
        if(userRepo.existsByUser(userDls.getUser()))
            return "Username already exist";
        userDls.setPassword(passwordEncoder.encode(userDls.getPassword()));
        userRepo.save(userDls);
        return "Success";
    }
}
