package com.main.gateway.security;

import com.main.gateway.model.UserDls;
import com.main.gateway.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class DataInitializer implements CommandLineRunner {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    /* This class only for add admin user details
    * */
    @Override
    public void run(String... args) {
        try{
            if(!userRepo.existsByUser("admin"))
                userRepo.save(new UserDls("admin", encoder.encode("admin123"),"ADMIN"));
        }catch (Exception e){
            log.error("userdls Table:"+e.getMessage());
        }
    }
}