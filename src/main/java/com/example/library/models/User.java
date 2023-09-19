package com.example.library.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "users")
public class User extends ApplicationEntity<User>{
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Username can not be blank")
    @Size(min = 2, max = 300, message = "Username must be between 2 and 300 characters")
    private String username;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Email can not be blank")
    @Email(message = "Invalid email format")
    @Size(max = 300, message = "Email must be less than 300 characters")
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "Password can not be blank")
    private String password;
}
