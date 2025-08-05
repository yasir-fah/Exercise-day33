package com.example.usermanagementsoftware.Controller;

import com.example.usermanagementsoftware.Api.ApiResponse;
import com.example.usermanagementsoftware.Model.User;
import com.example.usermanagementsoftware.Repository.UserRepository;
import com.example.usermanagementsoftware.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        userService.getAllUser();
        return ResponseEntity.status(200).body(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @Valid @RequestBody User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("user updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("user deleted successfully"));
    }

    @GetMapping("/get/authenticate/{username}/{password}")
    public ResponseEntity<?> checkUserInfo(@PathVariable String username,@PathVariable String password){
        User user = userService.checkUserInfo(username,password);
        return ResponseEntity.status(200).body(user);

    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email){
        User user = userService.getByEmail(email);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get/role/{role}")
    public ResponseEntity<?> getByRole(@PathVariable String role){
        List<User> user = userService.getByrole(role);
        return ResponseEntity.status(200).body(user);
    }

    @GetMapping("/get/range-age/{age}")
    public ResponseEntity<?> getByAgeRange(@PathVariable Integer age){
        List<User> user = userService.getByAgeRange(age);
        return ResponseEntity.status(200).body(user);
    }







}
