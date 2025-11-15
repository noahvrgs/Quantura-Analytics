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
    public User registerUser(User user, String rawPassword){
        user.setPasswordHash(encoder.encode(rawPassword));

        UserSettings settings = new UserSettings();
        user.setSettings(settings);

        return userRepo.save(user);
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
    
}
