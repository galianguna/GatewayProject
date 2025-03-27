package com.main.gateway.security;

import com.main.gateway.model.UserDls;
import com.main.gateway.repo.UserRepo;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@Log4j2
public class SecurityConfig {

    private final UserRepo userRepo;
    public SecurityConfig(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/GB/user/main","/user/register").permitAll()
                        .anyExchange().authenticated()
                )
                .httpBasic(withDefaults());//Basic Auth enabled
        //can access without credentials /GB/user/main
        return http.build();
    }

    /* method for check user credentials with DB or manual
    * */
    @Bean
    public ReactiveUserDetailsService reactiveUserDetailsService(PasswordEncoder passwordEncoder) {

        //Manual check and default credentials
        UserDetails user = User.builder()
                .username("Guna")
                .password(passwordEncoder.encode("123@"))
                .roles("ADMIN")
                .build();

        //Optional<UserDls> users = userRepo.findAllDetils();

        List<UserDetails> userDetailsList = new ArrayList<>();
        try{
            userDetailsList = userRepo.findAll().stream()
                    .map(this::convertToUserDetails)
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error("userdls Table:"+e.getMessage());
        }

        if(userDetailsList.isEmpty())
            userDetailsList = Arrays.asList(user);

        //returning all authorized user details
        return new MapReactiveUserDetailsService(userDetailsList);
    }
    private UserDetails convertToUserDetails(UserDls user) {

        //converting DB data into User details for authentication
        return User.builder()
                .username(user.getUser())  // using getUser() based on your entity
                .password(user.getPassword())
                .roles(user.getRole()) // or get roles from your entity if available
                .build();
    }
}