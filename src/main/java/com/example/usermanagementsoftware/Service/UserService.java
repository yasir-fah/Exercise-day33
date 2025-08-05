package com.example.usermanagementsoftware.Service;

import com.example.usermanagementsoftware.Api.ApiException;
import com.example.usermanagementsoftware.Model.User;
import com.example.usermanagementsoftware.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);

        if(oldUser == null){
            throw new ApiException("user not found");
        }

        oldUser.setName(user.getName());
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(user.getPassword());
        oldUser.setEmail(user.getEmail());
        oldUser.setRole(user.getRole());
        oldUser.setAge(user.getAge());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User oldUser = userRepository.findUserById(id);

        if(oldUser == null){
            throw new ApiException("user not found");
        }
        userRepository.delete(oldUser);
    }

    // 1:
    public User checkUserInfo(String username,String password){
        User user = userRepository.giveUserByAuthenticate(username);

        if(user.getId() == null){
            throw new ApiException("user not exist");
        }

        if(!user.getPassword().equals(password)){
            throw new ApiException("password not correct");
        }
        return user;
    }


    // 2:
    public User getByEmail(String email){
        User user = userRepository.findUserByEmail(email);

        if(user.getEmail() == null){
            throw new ApiException("user with this email not found");
        }

        return user;
    }

    public List<User> getByrole(String role){
        List<User> users = userRepository.giveMeByRole(role);

        if(users.isEmpty()) {
            throw new ApiException("no user with this role");
        }

        return users;
    }

    public List<User> getByAgeRange(Integer age){
        List<User> users = userRepository.getUserByAge(age);

        if(users.isEmpty()){
            throw new ApiException("no users found");
        }
        return users;
    }






}
