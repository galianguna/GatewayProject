package com.basic.user.impl;

import com.basic.user.model.User;
import com.basic.user.repo.UserRepo;
import com.basic.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    @Transactional
    public ResponseEntity<String> saveUser(User user) {
        try{
            if(userRepo.existsByEmail(user.getEmail()))
                return ResponseEntity.badRequest().body("Email already exists");
            if(!user.isNull())
                return ResponseEntity.badRequest().body("Name,Mail,Mobile must not null");
            if(!user.getMobile().isEmpty() && user.getMobile().length() != 10)
                return ResponseEntity.badRequest().body("Mobile number must be exactly 10 digits");
            userRepo.save(user);
            return ResponseEntity.ok("Success");
        }catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    @Override
    public List<User> getUser() {
        return userRepo.findAll();
    }

    @Override
    public ResponseEntity<String> updateUser(User user) {

        if(!user.getMobile().isEmpty() && user.getMobile().length() != 10)
            return ResponseEntity.badRequest().body("Mobile number must be exactly 10 digits");

        Optional<User> userDetails = userRepo.findById(user.getId());
        if(userDetails.isPresent()){
            userDetails.get().setName(user.getName()!=null ?user.getName():userDetails.get().getName());
            userDetails.get().setEmail(user.getEmail()!=null ? user.getEmail():userDetails.get().getEmail());
            userDetails.get().setMobile(user.getMobile()!=null ? user.getMobile():userDetails.get().getMobile());
            userRepo.save(userDetails.get());
            return ResponseEntity.ok("Success");
        }else {
            return ResponseEntity.ok("No data found");
        }
    }

    @Override
    public ResponseEntity<String> deleteUser(User user) {
        if(user.getId()==null)
            return ResponseEntity.badRequest().body("Id missing in Input");
        userRepo.deleteById(user.getId());
        return ResponseEntity.ok("Successfully Deleted");
    }
}
