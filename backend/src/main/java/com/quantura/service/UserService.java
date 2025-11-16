package com.quantura.service;

import com.quantura.model.User;
import com.quantura.model.UserSettings;
import com.quantura.repository.UserRepository;
import com.quantura.repository.UserSettingsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserSettingsRepository settingsRepo;
    private final PasswordEncoder encoder;

    public UserService(UserRepository userRepo, UserSettingsRepository settingsRepo, PasswordEncoder encoder){
        this.userRepo = userRepo;
        this.settingsRepo = settingsRepo;
        this.encoder = encoder;
    }

    @Override
    public User findById(Long id){
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    }

    @Override
    public User updateSettings(Long id, UserSettings newSettings){
        User user = findById(id);
        UserSettings currentSettings = user.getSettings();
        if(currentSettings == null){ currentSettings = new UserSettings(user); }

        currentSettings.setTheme(newSettings.getTheme());
        currentSettings.setEmailNotifications(newSettings.isEmailNotifications());
        currentSettings.setSmsNotifications(newSettings.isSmsNotifications());
        currentSettings.setPushNotifications(newSettings.isPushNotifications());
        currentSettings.setShowActivityStatus(newSettings.isShowActivityStatus());
        currentSettings.setAllowFriendRequests(newSettings.isAllowFriendRequests());
        currentSettings.setTimeZone(newSettings.getTimeZone());
        currentSettings.setDateFormat(newSettings.getDateFormat());

        return settingsRepo.save(currentSettings);
    }

    // ===== << Register a User w/ DTO Returns >> =====
    @Override
    public UserResponseDTO registerUser(RegisterRequestDTO req){
        org.apache.catalina.User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encoder(req.getPassword()));
        userRepo.save(user);

        UserSettings settings = new UserSettings(user);
        settingsRepo.save(settings);

        return new UserResponseDTO(user.getId(), user.getEmail());
    }

    /*
     *  NEEDS JWT LOGIC BEFORE IMPLEMENTATION
     */

    // ===== << User Login >> =====
    // @Override
    // public String login(LoginRequestDTO req){
    //     org.apache.catalina.User user = userRepo.findByEmail(req.getEmail()).orElseThrow(() -> new RuntimeException("Invalid Credentials."));

    //     if(!encoder.matches(req.getPassword(), user.getPassword())){
    //         throw new RuntimeException("Invalid Credentials.");
    //     }
    //     return jwtUtil.generateToken(user.getEmail());
    // }

    // ===== << Get a User by ID w/ DTO Response >> =====
    @Override
    public UserResponseDTO getUserByIdDTO(Long id){
        User user = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found."));
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getRole());
    }


    
}
