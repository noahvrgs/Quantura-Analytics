package com.quantura.controller;

import com.quantura.dto.*;
import com.quantura.service.UserService;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    // ===== << Create/Register New User >> ===== [Endpoint: POST]
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequestDTO req){
        UserResponseDTO response = userService.createUser(req);
        return ResponseEntity.ok(response);
    }

    // ===== << Login User >> ===== [Endpoint: POST]
    @PostMapping("login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequestDTO req) {
        String token = userService.login(req);
        return ResponseEntity.ok(token);
    }
    
    // ===== << Retrieve User Details using ID >> ===== [Endpoint: GET]
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id){
        UserResponseDTO response = userService.getUserByIdDTO(id);
        return ResponseEntity.ok(response);
    }

    // ===== << Update User Settings >> ===== [Endpoint: PUT]
    @PutMapping("/{id}/settings")
    public ResponseEntity<UserSettings> updateSettings(@PathVariable Long id, @RequestBody UserSettings settings){
        UserSettings update = userService.updateSettings(id, settings);
        return ResponseEntity.ok(update);
    }

}
