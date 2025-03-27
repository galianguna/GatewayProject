package com.main.gateway.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //entity of user repo
@NoArgsConstructor //added for create constructor
@AllArgsConstructor //added for create constructor
@Table(name = "userdls", uniqueConstraints = @UniqueConstraint(columnNames = "user"))
@Data // added for getter & setter
public class UserDls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String user;
    private String password;
    private String role;

    public UserDls(String user, String password, String role) {
        this.user = user;
        this.password = password;
        this.role = role;
    }

    @JsonIgnore //this method not interrupt in save
    public boolean isNull(){
        return user!=null && password!=null && role!=null;
    }

}
