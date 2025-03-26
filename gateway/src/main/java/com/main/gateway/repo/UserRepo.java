package com.main.gateway.repo;

import com.main.gateway.model.UserDls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepo extends JpaRepository<UserDls,Long> {

//    @Query(value = "SELECT * FROM UserDls", nativeQuery = true)
//    Optional<UserDls> findAllDetils();

    boolean existsByUser(String user);
}
