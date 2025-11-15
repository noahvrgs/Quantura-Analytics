package com.quantura.controller;

import com.quantura.model.User;
import com.quantura.model.UserSettings;
import com.quantura.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // ===== << Create/Register New User >> ===== [Endpoint: POST]
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        return ResponseEntity.ok(userService.createUser(user));
    }

    // ===== << Retrieve User Details using ID >> ===== [Endpoint: GET]
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // ===== << Update User Settings >> ===== [Endpoint: PUT]
    @PutMapping("/{id}/settings")
    public ResponseEntity<UserSettings> updateSettings(@PathVariable Long id, @RequestBody UserSettings settings){
        return ResponseEntity.ok(userService.updateSettings(id, settings));
    }

}
