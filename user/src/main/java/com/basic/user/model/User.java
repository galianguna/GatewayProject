package com.basic.user.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String email;
    private String mobile;

    @JsonIgnore
    public boolean isNull(){
        return name!=null && email!=null && mobile!=null;
    }

    @Transient //not save in DB
    @JsonIgnore //Not retrieve in object
    private String age;
}
