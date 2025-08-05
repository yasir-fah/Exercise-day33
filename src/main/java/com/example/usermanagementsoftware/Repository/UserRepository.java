package com.example.usermanagementsoftware.Repository;

import com.example.usermanagementsoftware.Model.User;
import jakarta.persistence.Column;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);

    @Query("select u from User u where u.username=:username")
    User giveUserByAuthenticate(String username);

    User findUserByEmail(String email);

    @Query("select u from User u where u.role=?1")
    List<User> giveMeByRole(String role);

    @Query("select u from User u where u.age >=?1")
    List<User> getUserByAge(Integer age);
}
