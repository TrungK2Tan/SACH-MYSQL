package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import com.example.demo.Validator.annotation.ValidUsename;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //sdafsafdas

    @Column(name = "username",length = 50,nullable = false,unique = true)
    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "User name must be less than 50 characters")
    @ValidUsename
    private String username;

    @Column(name = "password", length = 250,nullable = false)
    @NotBlank(message = "password is required")
    private  String password;

    @Column(name = "email",length = 50)
    @Size(max = 50, message = "Email must be less than 50 characters")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "name", length = 50, nullable = false)
    @Size(max = 50,message = "Your name must be less than 50 characters")
    @NotBlank(message = "Your name is required")
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();
}
