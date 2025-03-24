package com.basic.user.repo;

import com.basic.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
  boolean existsByEmail(String email);

  boolean existsByEmailAndId(String email,Long id);

}
