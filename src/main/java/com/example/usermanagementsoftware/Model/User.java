package com.example.usermanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty")
    @Size(min = 5, max = 20, message = "name length between 5-20")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "username can't be empty")
    @Size(min = 5, max = 20, message = "name length between 5-20")
    @Column(columnDefinition = "varchar(20) not null unique")
    private String username;

    @NotEmpty(message = "password can't be empty")
    private String password;

    @NotEmpty(message = "email can't be empty")
    @Email(message = "email must be valid")
//    @Column(columnDefinition = "varchar(100) not null unique")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "role can't be empty")
    @Pattern(regexp = "^(user|admin)$", message = "role should be: role|admin")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    @NotNull(message = "age can't be empty")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
